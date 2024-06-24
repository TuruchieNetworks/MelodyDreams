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

import com.turuchie.melodydreams.models.LikedSong;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.LikedSongService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.ArtistsUtils;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class LikedSongController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private LikedSongService likedSongServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private FileUtils fileUtil;

	@SuppressWarnings("unused")
	@Autowired
	private MetricsUtil metricUtil;
 
	@SuppressWarnings("unused")
	@Autowired
	private ArtistsUtils artistsUtil;

	@Autowired
	public LikedSongController(UserService userServ, LikedSongService likedSongServ,
		MetricsUtil metricUtil, SongService songServ, FileUtils fileUtil, ArtistsUtils artistsUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.artistsUtil = artistsUtil;
        this.metricUtil = metricUtil;
        this.likedSongServ = likedSongServ;
    }

	public LikedSongController() {}

	@GetMapping("/likedSong")
	public String LikedSongIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<LikedSong> allLikedSongs = likedSongServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allLikedSongs", allLikedSongs);
		return "LikedSongMedia/showAllLikedSongs.jsp";
	}

    
	@GetMapping("/likedSong/{id}")
	public String showOneLikedSong(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using LikedSongUtils
	    LikedSong oneMetric = likedSongServ.getOne(id);

	    if (oneMetric == null) {
	        // Handle the case where the LikedSong with the given id is not found
	        return "redirect:/melodydreams/likedSong";
	    }

        model.addAttribute("oneLikedSong", oneMetric);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "LikedSongMedia/showOneLikedSong.jsp";
	}

	@PostMapping("/process/createNewLikedSong/{songId}")
	public ResponseEntity<?> processCreateLikedSong(@PathVariable("songId") Long songId, 
	    @Valid @ModelAttribute("LikedSong") LikedSong likedSong, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
	    Long userId = fileUtil.validateUserAndGetId(session);
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }

	    User userLiking = artistsUtil.getUser(userId);
	    Long userLikingId = artistsUtil.getUserId(userLiking);

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }

	    Song songToLike = artistsUtil.getSong(songId);
	    Long songToLikeId = artistsUtil.getSongId(songToLike);
	    String successNotification = songToLike.getTrackTitle() + " " + "Liked Successfully!";
	    String failureNotification = songToLike.getTrackTitle() + " " + "Already Liked!";

	    // Check if the relationship already exists
	    if (likedSongServ.isRelationshipExists(songId, userLikingId)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(failureNotification);
	    }

	    try {
	        likedSong.setUser(userLiking);
	        likedSong.setLikingUserId(userLikingId);
	        likedSong.setSong(songToLike);
	        likedSong.setSongToLikeId(songToLikeId);
	        likedSongServ.create(likedSong);
	        return ResponseEntity.ok(successNotification);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

	@DeleteMapping("/deleteLikedSong/{likedSongId}")
	public ResponseEntity<String> deleteLikedSong(@PathVariable("likedSongId") Long likedSongId, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }

	    LikedSong likedSongToDelete = likedSongServ.getOne(likedSongId);

	    if (likedSongToDelete != null && likedSongToDelete.getLikingUserId().equals(userId)) {
	        try {
	            likedSongServ.delete(likedSongId);
	            return new ResponseEntity<>("LikedSong deleted successfully", HttpStatus.OK);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>("Error deleting LikedSong", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>("LikedSong not found or not owned by user", HttpStatus.FORBIDDEN);
	    }
	}

}

