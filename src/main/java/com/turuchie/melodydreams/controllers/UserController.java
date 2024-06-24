package com.turuchie.melodydreams.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.turuchie.melodydreams.models.LoginUser;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.ArtistsUtils;
import com.turuchie.melodydreams.utils.DateUtil;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;
import com.turuchie.melodydreams.utils.MusicListUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class UserController {
		@Autowired
		private UserService userServ;

		@Autowired
		private SongService songServ;

		@Autowired
		private DateUtil dateUtil;

		@Autowired
		private ArtistsUtils artistUtil;

		@Autowired
		private MetricsUtil metricsUtil;
		@Autowired
		private MusicListUtils musicListUtil;

		public UserController(UserService userServ, MetricsUtil metricsUtil, DateUtil dateUtil,
			MusicListUtils musicListUtil,SongService songServ, FileUtils fileUtil, ArtistsUtils artistUtil) {
	        this.userServ = userServ;
	        this.songServ = songServ;
	        this.dateUtil = dateUtil;
	        this.artistUtil = artistUtil;
	        this.metricsUtil = metricsUtil;
	        this.musicListUtil = musicListUtil;
	    }
	
		@GetMapping("/artists/{id}")
		public String showOneUser(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    } 

		User oneUser = userServ.getOne(id);
	    User loggedInUser = userServ.getOne(userId);

		if (oneUser == null) {
	        return "redirect:/melodydreams/login";
	    }

        metricsUtil.addMetricsToModel(model);
        dateUtil.addCurrentDateAttributes(model); 
	    musicListUtil.setMusicList(model, request);  
        metricsUtil.addPlaylistDataToModel(model, userId);
		musicListUtil.setUsersMusicList(model, request, userId);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("oneUser", oneUser);
		return "/Users/viewOneUser.jsp";
		}


		@GetMapping("/artists")
		public String showAllUsers(@RequestParam(value = "searchedArtist", required = false) String searchedArtist, 
		@ModelAttribute("playlist") Playlist playlist, Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    } 

	    List<Song> allSongs = songServ.getAll();

	    // Handle search and display
	    String trimmedSearchTerm = searchedArtist != null ? searchedArtist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        long searchedId = userServ.getOneUserByName(trimmedSearchTerm).getId();
	        metricsUtil.addPlaylistDataToModel(model, searchedId); 
	        musicListUtil.setUsersMusicList(model, request, searchedId);
	    } else {
	        // If the search bar is empty, display user playlists
		    metricsUtil.addMetricsToModel(model);
		    musicListUtil.setMusicList(model, request);  
	        metricsUtil.addPlaylistDataToModel(model, userId);  
	        musicListUtil.setUsersMusicList(model, request, userId);
	    }
       
        User loggedInUser = userServ.getOne(userId);
        dateUtil.addCurrentDateAttributes(model);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("playlistError", "Title Is Required!");		
		model.addAttribute("allSongs", allSongs);
		model.addAttribute("allUsers", userServ.findAll());

		return "Dashboard/Landing.jsp";
		}

		@GetMapping("/login")
		public String defaultLoginRegistration(@ModelAttribute("user") User user, @ModelAttribute("loginUser") LoginUser loginUser) {
			return "/Users/Login.jsp";
		}	
		
		@GetMapping("/register")
		public String loginRegUser(@ModelAttribute("user") User user, @ModelAttribute("loginUser") LoginUser loginUser) {
			return "/Users/Registration.jsp";
		}
		
		@PostMapping("/process/register")
		public String processRegister(@RequestParam(value = "dateOfBirth", required = false) LocalDate dateOfBirth,
		@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
			// reject if email is taken
			if(userServ.getOne(user.getEmail()) != null) {
				result.rejectValue("email", "Unique", "User Already Exits!");
			}
	
			// Check if email is already registered
			User existingUser = userServ.getOne(user.getEmail());
			if (existingUser != null) {
			    result.rejectValue("email", "Unique", "User with this email already exists!");
			}
	
			//reject if passwords don't match
			if(!user.getPassword().equals(user.getConfirmPassword())) {
				result.rejectValue("password", "Match", "Passwords Must Match!");
			}
	
		    // Validate birth date
		    if (dateOfBirth != null) {
			    // Validate birth date
			    if (!dateUtil.isValidBirthDate(dateOfBirth)) {
			        result.rejectValue("dateOfBirth", "InvalidDate", "Invalid birth date");
			    }
			}
	
			//redirect if errors
			if(result.hasErrors()) {
				model.addAttribute("loginUser", new LoginUser());
		        dateUtil.addCurrentDateAttributes(model);
				return "Users/Registration.jsp";
			}
	
			User newUser = userServ.registerUser(user);
			session.setAttribute("user_id", newUser.getId());	
			return "redirect:/melodydreams/artists";
		}
	
		@PostMapping("/process/login")
		public String processLoginUser(@Valid @ModelAttribute("loginUser") LoginUser loginUser, 
			BindingResult result, Model model, HttpSession session) {
			User loggingUser = userServ.login(loginUser, result);
			if (loggingUser == null || result.hasErrors()) {
			    model.addAttribute("user", new User());
			    dateUtil.addCurrentDateAttributes(model);
			    return "Users/Login.jsp";
			}
			session.setAttribute("user_id", loggingUser.getId());
			return "redirect:/melodydreams/artists";
		}

		@GetMapping("/editUser/{id}")
		public String editUser(@PathVariable("id") Long id, Model model, HttpSession session) {
		    Long userId = (Long) session.getAttribute("user_id");
		    if (userId == null) {
		            return "redirect:/mellowHealth/usersPortal/login";
		    } 

		    // Today's date 
	        dateUtil.addCurrentDateAttributes(model);
			model.addAttribute("user", userServ.getOne(id));
			model.addAttribute("confirmUserPassword", userServ.getOne(id).getPassword());
			return "Users/editUser.jsp";
		}
		
		@PatchMapping("/process/editUser/{id}")
		public String processEditUser(@PathVariable Long id, 
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("dateOfBirth") LocalDate dateOfBirth,
			@Valid @ModelAttribute("user") User existingUser,
		    BindingResult result, Model model, HttpSession session) {
		    Long userId = (Long) session.getAttribute("user_id");
		    if (result.hasErrors() || userId == null) {
		        // Add necessary model attributes for rendering the form with validation errors
		        if (userId != null) {
		            List<Integer> timeFormat = new ArrayList<>();
		            for (int i = 1; i <= 12; i++) {
		                timeFormat.add(i);
		            }

		            // Model attributes
		            model.addAttribute("timeFormat", timeFormat);
		            model.addAttribute("loggedInUser", userServ.getOne(userId));
					model.addAttribute("confirmPassword", existingUser.getConfirmPassword());
					model.addAttribute("user", userServ.getOne(id));
				    model.addAttribute("currentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		            return "Users/editUser.jsp";
		        } else {
		            // Handle the case where userId is null (redirect to login, show an error, etc.)
		            return "redirect:/mellowHealth/usersPortal/login";
		        }
		    } else {
		        User userToEdit = userServ.getOne(existingUser.getEmail());
		        // Reject if email is taken
		        if (userServ.existsByEmailAndIdNot(userToEdit.getEmail(), userId)) {
		            model.addAttribute("currentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		            result.rejectValue("email", "Unique", "Email is already taken by another user!");
		        }  

		        // Check if confirm password matches password
		        if (existingUser.getPassword().isEmpty() || existingUser.getConfirmPassword().isEmpty()) {
		            result.rejectValue("password", "Size", "Please Enter Password!");
		            result.rejectValue("confirmPassword", "Size", "Please Enter Password!");
		        }

	            // Remaining Field Values To update
		        artistUtil.setCommonUserAttributes(existingUser, userToEdit);
		    	
		    	
			    // Validate birth date
			    if (dateOfBirth != null) {
				    // Validate birth date
				    if (!dateUtil.isValidBirthDate(dateOfBirth)) {
				        result.rejectValue("dateOfBirth", "InvalidDate", "Invalid birth date");
				    }
				}

		        // Check if confirm password matches password
		        if (!existingUser.getPassword().equals(existingUser.getConfirmPassword())) {
		            result.rejectValue("confirmPassword", "Match", "Passwords Must Match!");
		        } else {
		        	userServ.setHashedPasswords(userToEdit, password, confirmPassword);
		        }

		        // Redirect if errors
		        if (result.hasErrors()) {
		            model.addAttribute("user", existingUser);
		            model.addAttribute("currentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		            return "Users/editUser.jsp";
		        }

		        // Save the updated user in the database
				userServ.update(userToEdit);
		        return "redirect:/melodydreams/artists/" + userToEdit.getId();
		    }
		}
		
		@DeleteMapping("/deleteUser/{id}")
		public String deleteUserProfile(@PathVariable("id") Long id, HttpSession session) {
		    Long userId = (Long) session.getAttribute("user_id");

		    // Redirect to login if userId is null
		    if (userId == null) {
		        return "redirect:/melodydreams/login";
		    }

		    User userProfileToDelete = userServ.getOne(id);

		    // Check if the logged-in user is the owner of the user
		    if (userProfileToDelete != null && userProfileToDelete.getId().equals(userId)) {
		        userServ.delete(id);
		    } else {
		        // Redirect to the melody dreams landing if the user is not the owner
		        return "redirect:/melodydreams/artists";
		    }

		    // Redirect to the melody dreams login after successful deletion
		    return "redirect:/melodydreams/login";
		}

	
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/melodydreams/login";
		}

}