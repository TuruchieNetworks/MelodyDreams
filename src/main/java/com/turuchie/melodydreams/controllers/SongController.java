 package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.exceptions.SomeAppropriateException;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.MetricsService;
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
public class SongController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private SongService songServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private FileUtils fileUtil;
	
	@Autowired
	private MetricsUtil metricsUtil;

	@Autowired
	private ArtistsUtils artistUtil;

	@Autowired
	private MusicListUtils musicListUtil;

	@Autowired
	public SongController( MetricsService metricsServ, UserService userServ,
		MetricsUtil metricsUtil, MusicListUtils musicListUtil, DateUtil dateUtil,
		SongService songServ, FileUtils fileUtil, ArtistsUtils artistUtil) {
        this.userServ = userServ;
        this.songServ = songServ;
        this.fileUtil = fileUtil;
        this.artistUtil = artistUtil;
        this.metricsUtil = metricsUtil;
        this.musicListUtil = musicListUtil;
    }

	public SongController() {}

    // Helper method to save file and return its bytes
    @SuppressWarnings("unused")
	private byte[] saveByteFile(MultipartFile file) throws IOException {
        java.nio.file.Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return Files.readAllBytes(filePath);
    }

	@GetMapping("/tracks")
	public String songIndexPage(@RequestParam(value = "searchedArtist", required = false) String searchedArtist, 
		Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<Song> allSongs = songServ.getAll();
	    // Handle search and display
	    String trimmedSearchTerm = searchedArtist != null ? searchedArtist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        List<Song> searchedSongs = songServ.getSongsByLetters(trimmedSearchTerm);
	        musicListUtil.setSearchedMusicList(model, request, searchedSongs);
	    } else {
	        // If the search bar is empty, display user playlists
            model.addAttribute("searchError", "No users found matching the provided term.");
            musicListUtil.setMusicList(model, request);
            artistUtil.setArtistAttributes(model, userId); 
	    }

	    // Today's date 
        dateUtil.addCurrentDateAttributes(model);

        // Prepare the music list JSON string using SongUtils
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

	    model.addAttribute("allSongs", allSongs);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allUsers", userServ.findAll());
		return "TrackMedia/showAllTracks.jsp";
	}

	@GetMapping("/tracks/{id}")
	public String showOneSong(@RequestParam(value = "searchedSong", required = false) String searchedSong, 
		@PathVariable("id") Long id, Model model, HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using SongUtils
	    Long oneSongId = id;
	    Song song = songServ.getOne(oneSongId);

	    if (song == null) {
	        // Handle the case where the song with the given id is not found
	        return "redirect:/melodydreams/tracks";
	    }

	    // Handle search and display
	    String trimmedSearchTerm = searchedSong != null ? searchedSong.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
		    List<Song> filteredSongs = new ArrayList<>();
	        //long searchedId = songServ.getOneTrackTitle(trimmedSearchTerm).getId();

		    filteredSongs = songServ.getSongsByLetters(trimmedSearchTerm);
	        //musicListUtil.setUsersMusicList(model, request, searchedId);
	        //musicListUtil.setSingleMusicList(model, request, searchedId);
	        musicListUtil.setSearchedMusicList(model, request, filteredSongs);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setSingleMusicList(model, request, oneSongId);
	    }


        metricsUtil.addMetricsToModel(model);
        dateUtil.addCurrentDateAttributes(model);
        musicListUtil.setUsersMusicList(model, request, userId);

        model.addAttribute("oneSong", song);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "TrackMedia/showOneTrack.jsp";
	}

	@GetMapping("/newTrack")
	public String createSong(@ModelAttribute("song") Song song,
		Model model, HttpSession session) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    List<Integer> timeFormat = new ArrayList<>();
	    for (int i = 1; i <= 12; i++) {
	        timeFormat.add(i);
	    }
	    model.addAttribute("timeFormat", timeFormat);

	    // Create a list of users and add the logged-in user to it
		List<User> users = new ArrayList<>();
	    User loggedInUser = userServ.getOne(userId);
		users.add(loggedInUser);

	    // Today's date 
        dateUtil.addCurrentDateAttributes(model);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "TrackMedia/addNewTrack.jsp";
	}

	@PostMapping("/process/createNewTrack")
	public String processCreateSong(
	    @RequestParam("imageData") MultipartFile imageData, 
	    @RequestParam("audioData") MultipartFile audioData,
	    @RequestParam(value = "genre", required = false) String genre, 
	    @RequestParam(value = "trackTitle", required = false) String trackTitle, 
	    @RequestParam(value = "trackArtist", required = false) String trackArtist, 
	    @RequestParam(value = "description", required = false) String description, 
	    @Valid @ModelAttribute("song") Song song, 
	    BindingResult result, Model model, HttpSession session) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    // Validate user session and get user ID
	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    // Set user attributes in the model
	    fileUtil.setUserAttributes(model, userId);

	    // Validate the song data and save files
	    try {
	        fileUtil.saveSongDataAndSetAttributes(song, imageData, audioData, userId);
	    } catch (IllegalArgumentException e) {
		    // Handle empty file error  
	        if (result.hasErrors() || FileUtils.validateTrackFields(result, audioData, imageData, description, trackArtist, trackTitle, trackTitle, model)) {
		        result.rejectValue("trackTitle", "error.trackTitle", "Invalid Track Title!");
		        result.rejectValue("trackArtist", "error.trackArtist", "Please enter Track Artist!");
		        result.rejectValue("genre", "error.genre", "Genre cannot be empty!");
		        result.rejectValue("description", "error.description", "Please describe your track!");

			    // Today's date 
		        dateUtil.addCurrentDateAttributes(model);
		        //fileUtil.handleFileValidationErrors(result, model, "Track Image File Is Empty!", "Audio File Is Empty!");        
		        if (!imageData.isEmpty()) {
			        //model.addAttribute("trackImageDataError", "Track Image File Is Empty!");
		            model.addAttribute("imageDataBase64", FileUtils.encodeToBase64(imageData));
		        }
	
		        if (!audioData.isEmpty()) {
			        //model.addAttribute("audioDataError", "Audio File Is Empty!");
		            model.addAttribute("audioDataBase64", FileUtils.encodeToBase64(audioData));
		        }
	           return "TrackMedia/addNewTrack.jsp";
	        }	
	    }
 
	    try {
	        // Save the song and redirect to the artist page upon successful submission
	        Song newSong = songServ.create(song);
	        long newSongUserId = newSong.getUser().getId();
	        return "redirect:/melodydreams/artists/" + newSongUserId;
	    } catch (SomeAppropriateException e) {
	        // Handle submission error
	        result.rejectValue("trackTitle", "error.song", "There was an error submitting the track. Please try again.");
		    return "TrackMedia/addNewTrack.jsp";
	    }
	}

	// Method to display the edit form
    @GetMapping("/editTrack/{id}")
    public String displayEditForm(@PathVariable("id") Long id,
    	Model model, HttpSession session, HttpServletRequest request) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/melodydreams/login";
        }

        fileUtil.setUserAttributes(model, userId);
        Song song = songServ.getOne(id);
        if (song == null || !song.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/tracks"; // or some error handling mechanism
        }

        model.addAttribute("song", song);
	    musicListUtil.setMusicList(model, request);
        musicListUtil.setSingleMusicList(model, request, id);
        musicListUtil.setUsersMusicList(model, request, userId);
        return "TrackMedia/editOneTrack.jsp"; // Adjust the path to your JSP file
    }

    @PatchMapping("/process/editTrack/{id}")
    public String updateTrack(@PathVariable("id") Long id,
        @RequestParam(value = "imageData", required = false) MultipartFile newImageData,
        @RequestParam(value = "audioData", required = false) MultipartFile newAudioData,
        @ModelAttribute("song") Song songToEdit, BindingResult result, 
        Model model, HttpSession session, HttpServletRequest request) throws IOException {
    	Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/melodydreams/login";
        }

        Song existingSong = songServ.getOne(id);
        if (existingSong == null || !existingSong.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/tracks"; // or some error handling mechanism
        }

        try {
            if (!newImageData.isEmpty() || !newAudioData.isEmpty()) {
                // Update and set song data
                fileUtil.updateSongDataAndSetAttributes(songToEdit, newImageData, newAudioData, userId);
            }

            // Call the update method in the service
            songServ.update(songToEdit);

            // Redirect to the track details page after successful update
            return "redirect:/melodydreams/tracks/" + existingSong.getId();
        } catch (IOException e) {
            // Handle file save error
	        dateUtil.addCurrentDateAttributes(model);
            fileUtil.handleFileSaveError(e, result, model);
            musicListUtil.setMusicList(model, request);
            musicListUtil.setSingleMusicList(model, request, id);
            musicListUtil.setUsersMusicList(model, request, userId);

            model.addAttribute("song", existingSong);
            model.addAttribute("loggedInUser", userServ.getOne(userId));
            model.addAttribute("timeFormat", fileUtil.generateTimeFormat());
            model.addAttribute("trackImageDataError", "Track Image File Is Empty!");
            model.addAttribute("audioDataError", "Audio File Is Empty!");
            return "TrackMedia/editOneTrack.jsp";
        }
    }

    // Confirm Track Delete
	@GetMapping("/deletetrack/{id}")
	public String confirmDeleteSong(@RequestParam(value = "searchedSong", required = false) String searchedSong, 
		@PathVariable("id") Long id, Model model, HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using SongUtils
	    Long oneSongId = id;
	    Song song = songServ.getOne(oneSongId);

	    if (song == null) {
	        // Handle the case where the song with the given id is not found
	        return "redirect:/melodydreams/tracks";
	    }

	    // Handle search and display
	    String trimmedSearchTerm = searchedSong != null ? searchedSong.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        long searchedId = songServ.getOneTrackTitle(trimmedSearchTerm).getId();
		    List<Song> filteredSongs = new ArrayList<>();
		    filteredSongs = songServ.getSongsByLetters(trimmedSearchTerm);
	        musicListUtil.setUsersMusicList(model, request, searchedId);
	        //musicListUtil.setSingleMusicList(model, request, searchedId);
	        musicListUtil.setSearchedMusicList(model, request, filteredSongs);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setUsersMusicList(model, request, userId);
	        musicListUtil.setSingleMusicList(model, request, oneSongId);
	    }


        metricsUtil.addMetricsToModel(model);
        dateUtil.addCurrentDateAttributes(model);
        //musicListUtil.setSingleMusicList(model, request, id);
        //musicListUtil.setUsersMusicList(model, request, userId);

        model.addAttribute("oneSong", song);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "TrackMedia/deleteOneTrack.jsp";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteSong(@PathVariable("id") Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");

	    // Redirect to login if userId is null
	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    Song songToDelete = songServ.getOne(id);

	    // Check if the logged-in user is the owner of the song
	    if (songToDelete != null && songToDelete.getUser().getId().equals(userId)) {
	        try {
	            // Delete the corresponding files from the file system
	            String trackCoverImageUrl = songToDelete.getTrackImageDataUrl() + "/" + songToDelete.getTrackImageFileName();
	            String audioFileUrl = songToDelete.getAudioDataUrl() + "/" + songToDelete.getAudioFileName();
	            String trackCoverImagePath = uploadDir + "/" + trackCoverImageUrl;
	            String audioFilePath = uploadDir + "/" + audioFileUrl;

	            Files.deleteIfExists(Paths.get(trackCoverImagePath));
	            Files.deleteIfExists(Paths.get(audioFilePath));

	            // Delete the song from the database
	            songServ.delete(id);
	        } catch (IOException e) {
	            // Handle exception if file deletion fails
	            e.printStackTrace();
	            // You may want to add a message indicating that file deletion failed
	        } catch (Exception ex) {
	            // Handle other exceptions that may occur during deletion
	            ex.printStackTrace();
	            // You may want to add a message indicating that deletion failed
	        }
	    } else {
	        // Redirect to the track listing page if the user is not the owner
	        return "redirect:/melodydreams/tracks";
	    }

	    // Redirect to the track listing page after successful deletion
	    return "redirect:/melodydreams/tracks";
	}

}

