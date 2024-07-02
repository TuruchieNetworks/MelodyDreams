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

import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.FollowedUserService;
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
public class FollowedUserController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private FollowedUserService followedUserServ;

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
	public FollowedUserController(UserService userServ, FollowedUserService followedUserServ,
		MetricsUtil metricUtil, SongService songServ, FileUtils fileUtil, TrackMediaUtils trackMediaUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.metricUtil = metricUtil;
        this.trackMediaUtil = trackMediaUtil;
        this.followedUserServ = followedUserServ;
    }

	public FollowedUserController() {}

	@GetMapping("/followedUser")
	public String FollowedUserIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<FollowedUser> allFollowedUsers = followedUserServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allFollowedUsers", allFollowedUsers);
		return "FollowedUserMedia/showAllFollowedUsers.jsp";
	}

    
	@GetMapping("/followedUser/{id}")
	public String showOneFollowedUser(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using FollowedUserUtils
	    FollowedUser oneMetric = followedUserServ.getOne(id);

	    if (oneMetric == null) {
	        // Handle the case where the FollowedUser with the given id is not found
	        return "redirect:/melodydreams/followedUser";
	    }

        model.addAttribute("oneFollowedUser", oneMetric);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "FollowedUserMedia/showOneFollowedUser.jsp";
	}

	@PostMapping("/process/createNewFollowedUser/{userToFollowId}")
	public ResponseEntity<?> processCreateFollowedUser(@PathVariable("userToFollowId") Long userToFollowId, 
	    @Valid @ModelAttribute("FollowedUser") FollowedUser newFollowedUser, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
	    Long userId = fileUtil.validateUserAndGetId(session);
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }
	    User userFollowing = trackMediaUtil.getUser(userId);
	    Long userFollowingId = trackMediaUtil.getUserId(userFollowing);
	    String userFollowingName = trackMediaUtil.getUserName(userFollowing);

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }

	    User userFollowed = trackMediaUtil.getUser(userToFollowId);
	    Long userFollowedId = trackMediaUtil.getUserId(userFollowed);
	    String userFollowedName = trackMediaUtil.getUserName(userFollowed);
	    String successNotification =  trackMediaUtil.generateSuccessNotification(userFollowed);
	    String failureNotification = trackMediaUtil.generateFailureNotification(userFollowed);

	    // Check if the relationship already exists
	    if (followedUserServ.isRelationshipExists(userFollowedId, userFollowingId)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(failureNotification);
	    }

	    try {	    
	    	trackMediaUtil.setNewFollowedUserAttributes(userFollowedId, userFollowingId, userFollowedName, userFollowingName, newFollowedUser, userFollowed);
	        followedUserServ.create(newFollowedUser);
	        return ResponseEntity.ok(successNotification);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

	// Unfollow a user
	@DeleteMapping("/deleteFollowedUser/{id}")
	public ResponseEntity<String> unfollowUser(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }

	    FollowedUser followedUser = followedUserServ.getOne(id);

	    if (followedUser != null && followedUser.getUser().getId().equals(userId)) {
	        try {
	            followedUserServ.delete(id);
	            return new ResponseEntity<>("User unfollowed successfully", HttpStatus.OK);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ResponseEntity<>("Error unfollowing user", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>("FollowedUser not found or not owned by user", HttpStatus.FORBIDDEN);
	    }
	}

}

