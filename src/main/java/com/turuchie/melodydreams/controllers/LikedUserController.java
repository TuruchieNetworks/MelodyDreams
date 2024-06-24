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

import com.turuchie.melodydreams.models.LikedUser;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.LikedUserService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;
import com.turuchie.melodydreams.utils.ArtistsUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class LikedUserController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private LikedUserService likedUserServ;

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
	public LikedUserController(UserService userServ, LikedUserService likedUserServ,
		MetricsUtil metricUtil, SongService songServ, FileUtils fileUtil, ArtistsUtils artistsUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.artistsUtil = artistsUtil;
        this.metricUtil = metricUtil;
        this.likedUserServ = likedUserServ;
    }

	public LikedUserController() {}

	@GetMapping("/likedUser")
	public String LikedUserIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<LikedUser> allLikedUsers = likedUserServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allLikedUsers", allLikedUsers);
		return "LikedUserMedia/showAllLikedUsers.jsp";
	}

    
	@GetMapping("/likedUser/{id}")
	public String showOneLikedUser(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using LikedUserUtils
	    LikedUser oneLikedUser= likedUserServ.getOne(id);

	    if (oneLikedUser == null) {
	        // Handle the case where the LikedUser with the given id is not found
	        return "redirect:/melodydreams/likedUser";
	    }

        model.addAttribute("oneLikedUser", oneLikedUser);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "LikedUserMedia/showOneLikedUser.jsp";
	}

	@PostMapping("/process/createNewLikedUser/{userToLikeId}")
	public ResponseEntity<?> processCreateLikedUser(@PathVariable("userToLikeId") Long userToLikeId, 
	    @Valid @ModelAttribute("LikedUser") LikedUser newLikedUser, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
		Long userId = fileUtil.validateUserAndGetId(session);
	    User likingUser = artistsUtil.getUser(userId);
	    String likingUserName = artistsUtil.getUserName(likingUser);
	    Long likingUserId = userId;

	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }

	    User userLiked = artistsUtil.getUser(userToLikeId);
	    Long userLikedId = artistsUtil.getUserId(userLiked);
	    String userLikedName = artistsUtil.getUserName(userLiked);

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }

	    // Check if the relationship already exists
	    if (likedUserServ.isRelationshipExists(userLikedId, likingUserId)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(artistsUtil.generateLikeFailureNotification(userLiked));
	    }

	    try {
	    	artistsUtil.setNewLikeUserAttributes(newLikedUser, likingUser, userLikedId, likingUserId, userLikedName, likingUserName);
	        likedUserServ.create(newLikedUser);
	        return ResponseEntity.ok(artistsUtil.generateLikeSuccessNotification(userLiked));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

	@DeleteMapping("/deleteLikedUser/{id}")
	public ResponseEntity<String> deleteLikedUser(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }

	    LikedUser likedUserToDelete = likedUserServ.getOne(id);

	    if (likedUserToDelete != null && likedUserToDelete.getUser().getId().equals(userId)) {
	        try {
	            likedUserServ.delete(id);
	            return new ResponseEntity<>("LikedUser deleted successfully", HttpStatus.OK);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>("Error deleting LikedUser", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>("LikedUser not found or not owned by user", HttpStatus.FORBIDDEN);
	    }
	}
}

