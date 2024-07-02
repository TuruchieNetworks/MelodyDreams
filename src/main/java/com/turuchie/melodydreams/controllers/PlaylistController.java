 package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.PlaylistSongs;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.PlaylistSongsService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.ArtistsUtils;
import com.turuchie.melodydreams.utils.DateUtil;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MusicListUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/melodydreams")
public class PlaylistController {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private UserService userServ;

	@Autowired
	private SongService songServ;

	@Autowired
	private PlaylistService playlistServ;

	@Autowired
	private PlaylistSongsService playlistSongServ;

	@Autowired
	private FileUtils fileUtil;

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ArtistsUtils artistUtil;

	@Autowired
	private MusicListUtils musicListUtil;

	@Autowired
	public PlaylistController(PlaylistService playlistServ,  UserService userServ, SongService songServ, 
		PlaylistSongsService playlistSongServ, ArtistsUtils artistUtil, DateUtil dateUtil, MusicListUtils musicListUtil, FileUtils fileUtil) {
        this.userServ = userServ;
        this.songServ = songServ;
        this.fileUtil = fileUtil;
        this.playlistServ = playlistServ;
        this.musicListUtil = musicListUtil;
        this.playlistSongServ = playlistSongServ;
    }

	public PlaylistController() {}

	@GetMapping("/playlists")
	public String playlistIndexPage(@RequestParam(value = "searchedPlaylist", required = false) String searchedPlaylist, 
		Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<Playlist> allPlaylists = playlistServ.getAll();

	    // Handle search and display
	    String trimmedSearchTerm = searchedPlaylist != null ? searchedPlaylist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        List<Playlist> searchedPlaylists = playlistServ.getPlaylistsByLetters(trimmedSearchTerm);
	        musicListUtil.setPlaylistMusicList(model, request, searchedPlaylists);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setPlaylistMusicList(model, request, allPlaylists);
	    }

	    // Today's date 
        dateUtil.addCurrentDateAttributes(model);

        // Prepare the music list JSON string using PlaylistUtils
        musicListUtil.setMusicList(model, request);
	    artistUtil.setArtistAttributes(model, userId);
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

	    model.addAttribute("allPlaylists", allPlaylists);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allUsers", userServ.findAll());
		return "Playlists/showAllPlaylists.jsp";
	}

	@GetMapping("/playlists/{id}")
	public String showOnePlaylist(@RequestParam(value = "searchedPlaylist", required = false) String searchedPlaylist, 
		@PathVariable("playlistId") Long playlistId, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using PlaylistUtils
	    Playlist playlist = playlistServ.getOne(playlistId);

	    if (playlist == null) {
	        // Handle the case where the playlist with the given id is not found
	        return "redirect:/melodydreams/playlists";
	    }

	    Long playlistUserId = playlist.getUser().getId();
        List<Playlist> userPlaylists = playlist.getUser().getPlaylists();

        if (playlist == null || !playlist.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/playlists"; // or some error handling mechanism
        }
        // Handle search and display
	    String trimmedSearchTerm = searchedPlaylist != null ? searchedPlaylist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        List<Playlist> searchedPlaylists = playlistServ.getPlaylistsByLetters(trimmedSearchTerm);
	        musicListUtil.setPlaylistMusicList(model, request, searchedPlaylists);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setPlaylistMusicList(model, request, userPlaylists);
	    }

        model.addAttribute("onePlaylist", playlist);
	    model.addAttribute("loggedInUser", loggedInUser);
	    model.addAttribute("allPlaylists", playlistServ.getAll());
        musicListUtil.setUsersMusicList(model, request, playlistUserId);
	    return "Playlists/showOnePlaylist.jsp";
	}

	@GetMapping("/newPlaylist") 
	public String createPlaylist(@ModelAttribute("playlist") Playlist playlist,
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
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "Playlists/addNewPlaylist.jsp";
	}

	@PostMapping("/process/createNewPlaylist/{songId}")
	public ResponseEntity<?> processCreatePlaylist(
	    @PathVariable("songId") Long songId,
	    @Valid @ModelAttribute("playlist") Playlist playlist, 
	    BindingResult result, 
	    Model model, 
	    HttpSession session) {

	    Long userId = (Long) session.getAttribute("user_id");

	    // Validate user session and get user ID
	    if (userId == null || songId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User not logged in\"}");
	    }

	    User loggedUser = userServ.getOne(userId);
	    Song songToAddToPlaylist = songServ.getOne(songId);

	    // Handle validation errors
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("\"message\": \"Title is required\"");
	    }
	    
	    try {
	        // Save the playlist and the playlist// Playlist playlistToAdd = new Playlist();
	        Playlist newPlaylist = new Playlist();
	        newPlaylist.setUser(loggedUser);
	        newPlaylist.setTitle(playlist.getTitle());
	        newPlaylist.setDescription(playlist.getDescription());
	        newPlaylist = playlistServ.create(newPlaylist);
		    
	        PlaylistSongs newPlaylistSong = new PlaylistSongs();
	        newPlaylistSong.setPlaylist(newPlaylist);
	        newPlaylistSong.setUser(loggedUser);
	        newPlaylistSong.setSong(songToAddToPlaylist);
	        newPlaylistSong.setPlaylist(newPlaylist);
	        playlistSongServ.create(newPlaylistSong);

	        return ResponseEntity.ok("{\"message\": \"Playlist created successfully\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"An error occurred\"}");
	    }
	}

	@PostMapping("/process/createNewPlaylistSong/{songId}")
	public ResponseEntity<?> processCreatePlaylistSong(
	    @PathVariable("songId") Long songId,
	    @Valid @ModelAttribute("playlist") PlaylistSongs playlistSong, 
	    BindingResult result, 
	    Model model, 
	    HttpSession session) {

	    Long userId = (Long) session.getAttribute("user_id");

	    // Validate user session and get user ID
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User not logged in\"}");
	    }

	    User loggedUser = userServ.getOne(userId);
	    Song playlistSongToAdd = songServ.getOne(songId);

	    // Handle validation errors
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("\"message\": \"Title is required\"");
	    }
	    
	    try {
	        // Save the playlist and the playlist
	        Playlist newPlaylist = playlistSong.getPlaylist();

	        PlaylistSongs newPlaylistSong = new PlaylistSongs();
	        newPlaylistSong.setUser(loggedUser);
	        newPlaylistSong.setPlaylist(newPlaylist);
	        newPlaylistSong.setSong(playlistSongToAdd);
	        playlistSongServ.create(newPlaylistSong);

	        return ResponseEntity.ok("{\"message\": \"Playlist Added To Playlist Successfully\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"An error occurred\"}");
	    }
	}	

	// Method to display the edit form
    @GetMapping("/editPlaylist/{id}")
    public String displayEditForm(@RequestParam(value = "searchedPlaylist", required = false) String searchedPlaylist, 
    	@PathVariable("playlistId") Long playlistId, Model model, HttpSession session, HttpServletRequest request) {
        Long userId = (Long) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/melodydreams/login";
        }

        fileUtil.setUserAttributes(model, userId);
        Playlist playlist = playlistServ.getOne(playlistId);
        Long playlistUserId = playlist.getUser().getId();
        List<Playlist> userPlaylists = playlist.getUser().getPlaylists();

        if (playlist == null || !playlist.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/playlists"; // or some error handling mechanism
        }
        // Handle search and display
	    String trimmedSearchTerm = searchedPlaylist != null ? searchedPlaylist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        List<Playlist> searchedPlaylists = playlistServ.getPlaylistsByLetters(trimmedSearchTerm);
	        musicListUtil.setPlaylistMusicList(model, request, searchedPlaylists);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setPlaylistMusicList(model, request, userPlaylists);
	    }

	    model.addAttribute("playlist", playlist);
        musicListUtil.setUsersMusicList(model, request, playlistUserId);
        return "Playlists/editOnePlaylist.jsp"; // Adjust the path to your JSP file
    }

    @PatchMapping("/process/editPlaylist/{id}")
    public String updateTrack(@PathVariable("id") Long id,
        @RequestParam(value = "imageData", required = false) MultipartFile newImageData,
        @RequestParam(value = "audioData", required = false) MultipartFile newAudioData,
        @ModelAttribute("playlist") Playlist playlistToEdit, BindingResult result, 
        Model model, HttpSession session, HttpServletRequest request) throws IOException {
    	Long userId = (Long) session.getAttribute("user_id");

    	if (userId == null) {
            return "redirect:/melodydreams/login";
        }

        Playlist existingPlaylist = playlistServ.getOne(id);
        if (existingPlaylist == null || !existingPlaylist.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/playlists"; // or some error handling mechanism
        }

        if (!result.hasErrors()) {
            // Update and set playlist data
            musicListUtil.setMusicList(model, request);
            musicListUtil.setSingleMusicList(model, request, id);
            musicListUtil.setUsersMusicList(model, request, userId);
            model.addAttribute("playlist", existingPlaylist);
            model.addAttribute("loggedInUser", userServ.getOne(userId));
            model.addAttribute("timeFormat", fileUtil.generateTimeFormat());
            return "Playlists/editOnePlaylist.jsp"; 
        }


        // Call the update method in the service
		playlistServ.update(playlistToEdit);

		// Redirect to the track details page after successful update
		return "redirect:/melodydreams/playlists/" + existingPlaylist.getId();
    }

    // Confirm Playlist Delete
	@GetMapping("/confirmDeletePlaylist/{id}")
	public String confirmDeletePlaylist(@RequestParam(value = "searchedPlaylist", required = false) String searchedPlaylist, 
		@PathVariable("id") Long playlistId, Model model, HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using PlaylistUtils
	    Long onePlaylistId = playlistId;
	    Playlist onePlaylist = playlistServ.getOne(onePlaylistId);

	    if (onePlaylist == null) {
	        // Handle the case where the playlist with the given id is not found
	        return "redirect:/melodydreams/tracks";
	    }

	    List<Playlist> allPlaylists = playlistServ.getAll();

	    // Handle search and display
	    String trimmedSearchTerm = searchedPlaylist != null ? searchedPlaylist.trim() : null;
	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        List<Playlist> searchedPlaylists = playlistServ.getPlaylistsByLetters(trimmedSearchTerm);
	        musicListUtil.setPlaylistMusicList(model, request, searchedPlaylists);
	    } else {
	        // If the search bar is empty, display user playlists
	        musicListUtil.setPlaylistMusicList(model, request, allPlaylists);
	    }

	    dateUtil.addCurrentDateAttributes(model);
        model.addAttribute("onePlaylist", onePlaylist);
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "Playlists/deleteOnePlaylist.jsp";
	}

	@DeleteMapping("/deletePlaylist/{id}")
	public String deletePlaylist(@PathVariable("id") Long id, HttpSession session) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    // Redirect to login if userId is null
	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    Playlist playlistToDelete = playlistServ.getOne(id);

	    // Check if the logged-in user is the owner of the playlist
	    if (playlistToDelete != null && playlistToDelete.getUser().getId().equals(userId)) {
	        try {
	            // Delete the playlist from the database
	            playlistServ.delete(id);
	        } catch (Exception ex) {
	            // Handle other exceptions that may occur during deletion
	            ex.printStackTrace();
	            // You may want to add a message indicating that deletion failed
	        }
	    } else {
	        // Redirect to the track listing page if the user is not the owner
	        return "redirect:/melodydreams/playlists";
	    }

	    // Redirect to the track listing page after successful deletion
	    return "redirect:/melodydreams/playlists";
	}

}

