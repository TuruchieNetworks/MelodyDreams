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

import com.turuchie.melodydreams.models.FavoriteSong;
import com.turuchie.melodydreams.models.FavoriteSong;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.FavoriteSongService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.TrackMediaUtils;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class FavoriteSongsController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private FavoriteSongService favoriteSongServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private FileUtils fileUtil;
 
	@Autowired
	private TrackMediaUtils trackMediaUtil;

	@Autowired
	public FavoriteSongsController(UserService userServ, FavoriteSongService favoriteSongServ,
		MetricsUtil metricUtil, SongService songServ, FileUtils fileUtil, TrackMediaUtils trackMediaUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.trackMediaUtil = trackMediaUtil;
        this.favoriteSongServ = favoriteSongServ;
    }

	public FavoriteSongsController() {}

	@GetMapping("/favoriteSongs")
	public String FavoriteSongIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<FavoriteSong> allFavoriteSongs = favoriteSongServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allFavoriteSongs", allFavoriteSongs);
		return "FavoriteSongs/showAllFavoriteSongs.jsp";
	}

    
	@GetMapping("/favoriteSongs/{id}")
	public String showOneFavoriteSong(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using FavoriteSongUtils
	    FavoriteSong oneFavoriteSong = favoriteSongServ.getOne(id);

	    if (oneFavoriteSong == null) {
	        // Handle the case where the FavoriteSong with the given id is not found
	        return "redirect:/melodydreams/favoriteSong";
	    }

        model.addAttribute("oneFavoriteSong", oneFavoriteSong);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "FavoriteSongs/showOneFavoriteSong.jsp";
	}

	@PostMapping("/process/createNewFavoriteSong/{songId}")
	public ResponseEntity<?> processCreateFavoriteSong(@PathVariable("songId") Long songId,
	    @Valid @ModelAttribute("FavoriteSong") FavoriteSong newFavoriteSong, BindingResult result, Model model,
	    HttpSession session) throws IOException {
	    Long userId = fileUtil.validateUserAndGetId(session);

	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }

	    User userFavoriting = trackMediaUtil.getUser(userId);
	    Long favoritedSongId = songId;

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }

	    Song favoritedSong = trackMediaUtil.getSong(favoritedSongId);
	    String successNotification = trackMediaUtil.generateFavoriteSongsSuccessNotification(favoritedSong);
	    String failureNotification = trackMediaUtil.generateFavoriteSongsFailureNotification(favoritedSong);

	    // Check if the relationship already exists
	    if (favoriteSongServ.isRelationshipExists(favoritedSongId, userId)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(failureNotification); // Conflict if already favorited
	    }

	    try {
	        trackMediaUtil.setNewFavoriteSongAttributes(newFavoriteSong, userFavoriting, favoritedSong, favoritedSongId);
	        favoriteSongServ.createIfNotExists(newFavoriteSong);
	        return ResponseEntity.ok(successNotification);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

	@DeleteMapping("/deleteFavoriteSong/{id}")
	public ResponseEntity<String> deleteFavoriteSong(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }

	    FavoriteSong favoriteSong = favoriteSongServ.getOne(id);
	    Song favoritedSong = favoriteSong.getSong();
	    String trackTitle = favoriteSong.getSong().getTrackTitle();
	    String failureNotification = "Error Removing" + " " + favoritedSong + " " + "From Favorites";
	    String successNotification = trackTitle + " " + "Was Successfully Removed From Favorites";

	    if (favoriteSong != null && favoriteSong.getUser().getId().equals(userId)) {
	        try {
	            favoriteSongServ.delete(id);
	            return new ResponseEntity<>(successNotification, HttpStatus.OK);// "User un-followed successfully", HttpStatus.OK
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>(failureNotification, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>("FavoriteSong not found or not owned by user", HttpStatus.FORBIDDEN);
	    }
	}
}
