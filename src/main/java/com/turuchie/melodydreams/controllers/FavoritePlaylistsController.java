package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turuchie.melodydreams.models.FavoritePlaylist;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.FavoritePlaylistService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;
import com.turuchie.melodydreams.utils.TrackMediaUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class FavoritePlaylistsController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private FavoritePlaylistService favoritePlaylistServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private FileUtils fileUtil;

	@SuppressWarnings("unused")
	@Autowired
	private MetricsUtil metricUtil;
 
	@Autowired
	private TrackMediaUtils trackMediaUtil;

	@Autowired
	public FavoritePlaylistsController(UserService userServ, FavoritePlaylistService favoritePlaylistServ,
		MetricsUtil metricUtil, SongService songServ, FileUtils fileUtil, TrackMediaUtils trackMediaUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.metricUtil = metricUtil;
        this.trackMediaUtil = trackMediaUtil;
        this.favoritePlaylistServ = favoritePlaylistServ;
    }

	public FavoritePlaylistsController() {}

	@GetMapping("/favoritePlaylists")
	public String FavoritePlaylistIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<FavoritePlaylist> allFavoritePlaylists = favoritePlaylistServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allFavoritePlaylists", allFavoritePlaylists);
		return "FavoritePlaylists/showAllFavoritePlaylists.jsp";
	}

    
	@GetMapping("/favoritePlaylists/{id}")
	public String showOneFavoritePlaylist(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using FavoritePlaylistUtils
	    FavoritePlaylist oneFavoritePlaylist = favoritePlaylistServ.getOne(id);

	    if (oneFavoritePlaylist == null) {
	        // Handle the case where the FavoritePlaylist with the given id is not found
	        return "redirect:/melodydreams/favoritePlaylist";
	    }

        model.addAttribute("oneFavoritePlaylist", oneFavoritePlaylist);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "FavoritePlaylists/showOneFavoritePlaylist.jsp";
	}

	@PostMapping("/process/createNewFavoritePlaylist/{playlistId}")
	public ResponseEntity<?> processCreateFavoritePlaylist(@PathVariable("playlistId") Long playlistId, 
	    @Valid @ModelAttribute("FavoritePlaylist") FavoritePlaylist newFavoritePlaylist, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
	    Long userId = fileUtil.validateUserAndGetId(session);

	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }

	    User userFavoriting = trackMediaUtil.getUser(userId);
	    Long userFavoritingId = trackMediaUtil.getUserId(userFavoriting);
	    User favoritedUser = trackMediaUtil.getPlaylistUser(newFavoritePlaylist);
	    String playlistTitle = trackMediaUtil.getPlaylistTitle(newFavoritePlaylist);
	    String playlistDescription = trackMediaUtil.getPlaylistDescription(newFavoritePlaylist);

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }

	    Playlist favoritedPlaylist = trackMediaUtil.getPlaylist(playlistId);
	    Long favoritedPlaylistId = trackMediaUtil.getPlaylistId(favoritedPlaylist);
	    Long favoritedPlaylistUserId = trackMediaUtil.getUserIdFromFavoritedPlaylist(playlistId);
	    String successNotification =  trackMediaUtil.generateFavoritePlaylistsSuccessNotification(favoritedPlaylist);
	    String failureNotification = trackMediaUtil.generateFavoritePlaylistsFailureNotification(favoritedPlaylist);

	    // Check if the relationship already exists
	    if (favoritePlaylistServ.isRelationshipExists(userFavoritingId, favoritedPlaylistId)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(failureNotification);
	    }

	    try {	    
	    	trackMediaUtil.setNewFavoritePlaylistAttributes(newFavoritePlaylist, favoritedUser, favoritedPlaylistUserId, favoritedPlaylistId, favoritedPlaylist, playlistDescription, playlistTitle);
	        favoritePlaylistServ.create(newFavoritePlaylist);
	        return ResponseEntity.ok(successNotification);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

	@DeleteMapping("/deleteFavoritePlaylist/{id}")
	public ResponseEntity<String> unfollowUser(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }

	    FavoritePlaylist favoritePlaylist = favoritePlaylistServ.getOne(id);
	    //Playlist favoritedPlaylist = favoritePlaylist.getPlaylist();
	    String playlistTitle = favoritePlaylist.getPlaylist().getTitle();
	    String failureNotification = "Error Removing" + " " + playlistTitle + " " + "From Favorites";
	    String successNotification = playlistTitle + " " + "Was Successfully Removed From Favorites";

	    if (favoritePlaylist != null && favoritePlaylist.getUser().getId().equals(userId)) {
	        try {
	            favoritePlaylistServ.delete(id);
	            return new ResponseEntity<>(successNotification, HttpStatus.OK);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>(failureNotification, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>("FavoritePlaylist not found or not owned by user", HttpStatus.FORBIDDEN);
	    }
	}
}

