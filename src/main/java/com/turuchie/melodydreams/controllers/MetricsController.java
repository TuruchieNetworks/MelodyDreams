package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.MetricsService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class MetricsController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private MetricsService metricsServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private SongService songServ;

	@Autowired
	private FileUtils fileUtil;

	@Autowired
	private MetricsUtil metricsUtil;

	@Autowired
	public MetricsController(UserService userServ, MetricsService metricsServ,
		MetricsUtil metricsUtil, SongService songServ, FileUtils fileUtil) {
        this.fileUtil = fileUtil;
        this.userServ = userServ;
        this.songServ = songServ;
        this.metricsUtil = metricsUtil;
        this.metricsServ = metricsServ;
    }

	public MetricsController() {}

	@GetMapping("/metrics")
	public String MetricsIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<Metrics> allMetricss = metricsServ.getAll();
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allMetricss", allMetricss);
		return "MetricsMedia/showAllMetricss.jsp";
	}

    @GetMapping("/metrics/data/{songId}")
    @ResponseBody
    public Map<String, Object> getMetricsData(@PathVariable("songId") Long songId, Model model) {
        Song song = songServ.getOne(songId);
        Map<String, Object> response = new HashMap<>();
        response.put("trackTitle", song.getTrackTitle());
        metricsUtil.addMetricsToModel(model);
        response.put("playCountSize", song.getMetrics().stream().mapToInt(Metrics::getPlayCount).sum());
        return response;
    }
    
	@GetMapping("/metrics/{id}")
	public String showOneMetrics(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using MetricsUtils
	    Metrics oneMetric = metricsServ.getOne(id);

	    if (oneMetric == null) {
	        // Handle the case where the Metrics with the given id is not found
	        return "redirect:/melodydreams/metrics";
	    }

        model.addAttribute("oneMetrics", oneMetric);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "MetricsMedia/showOneMetrics.jsp";
	}

	@PostMapping("/process/createNewMetrics/{songId}")
	public ResponseEntity<?> processCreateMetrics(@PathVariable("songId") Long songId, 
	    @Valid @ModelAttribute("Metrics") Metrics metrics, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
	    Long userId = fileUtil.validateUserAndGetId(session);
	    Song metrixSong = songServ.getOne(songId);
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	    }
	    User metrixUser = userServ.getOne(userId);
	
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Validation error");
	    }
	
	    try {
	    	metrics.setUser(metrixUser);
	    	metrics.setSong(metrixSong);
	        metricsServ.create(metrics);
	        return ResponseEntity.ok("Metrics created successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}


//	@PostMapping("/process/likedSongs/likeSong")
//    public ResponseEntity<?> likeSong(@RequestParam("songId") Long songId, HttpSession session) {
//        Long userId = (Long) session.getAttribute("user_id");
//
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//        }
//        User loggedUser = userServ.getOne(userId);
//        Song songToLike = songServ.getOne(songId);
//
//        try {
//            LikedSong likedSong = new LikedSong();
//            likedSong.setUser(loggedUser);
//            likedSong.setSong(songToLike);
//            likedSongServ.create(likedSong);
//
//            return ResponseEntity.ok("Song liked successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }

//    @PostMapping("/process/likedUsers/likeUser")
//    public ResponseEntity<?> likeUser(@RequestParam("userId") Long userId, HttpSession session) {
//        Long currentUserId = (Long) session.getAttribute("user_id");
//
//        if (currentUserId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//        }
//        User loggedUser = userServ.getOne(currentUserId);
//        User userToLike = userServ.getOne(userId);
//
//        try {
//            LikedUser likedUser = new LikedUser();
//            likedUser.setLikingUser(loggedUser);
//            likedUser.setLikedUser(userToLike);
//            likedUserServ.create(likedUser);
//
//            return ResponseEntity.ok("User liked successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }
//
//    @PostMapping("/process/followedUsers/followUser")
//    public ResponseEntity<?> followUser(@RequestParam("userId") Long userId, HttpSession session) {
//        Long currentUserId = (Long) session.getAttribute("user_id");
//
//        if (currentUserId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//        }
//        User loggedUser = userServ.getOne(currentUserId);
//        User userToFollow = userServ.getOne(userId);
//
//        try {
//            FollowedUser followedUser = new FollowedUser();
//            followedUser.setFollowingUser(loggedUser);
//            followedUser.setFollowedUser(userToFollow);
//            followedUserServ.create(followedUser);
//
//            return ResponseEntity.ok("User followed successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }


//	@GetMapping("/newMetrics")
//	public String createMetrics(@ModelAttribute("Metrics") Metrics Metrics,
//		Model model, HttpSession session) throws IOException {
//	    Long userId = (Long) session.getAttribute("user_id");
//	    if (userId == null) {
//	        return "redirect:/melodydreams/login";
//	    }
//
//	    // Create a list of users and add the logged-in user to it
//		List<User> users = new ArrayList<>();
//	    User loggedInUser = userServ.getOne(userId);
//		users.add(loggedInUser);
//	    model.addAttribute("loggedInUser", loggedInUser);
//	    return "MetricsMedia/addNewMetrics.jsp";
//	}

	@DeleteMapping("/deleteMetrics/{id}")
	public String deleteMetrics(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    // Redirect to login if userId is null
	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    Metrics MetricsToDelete = metricsServ.getOne(id);

	    // Check if the logged-in user is the owner of the Metrics
	    if (MetricsToDelete != null && MetricsToDelete.getUser().getId().equals(userId)) {
	        try {
	            // Delete the Metrics from the database
	            metricsServ.delete(id);
	        } catch (Exception ex) {
	            // Handle other exceptions that may occur during deletion
	            ex.printStackTrace();
	            // You may want to add a message indicating that deletion failed
	        }
	    } else {
	        // Redirect to the Metrics listing page if the user is not the owner
	        return "redirect:/melodydreams/metrics";
	    }

	    // Redirect to the Metrics listing page after successful deletion
	    return "redirect:/melodydreams/metrics";
	}

}

