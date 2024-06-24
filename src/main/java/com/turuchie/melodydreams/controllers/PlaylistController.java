 package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	private SongService songServ;

	@Autowired
	private PlaylistService playlistServ;

	@Autowired
	private PlaylistSongsService playlistSongServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private FileUtils fileUtil;

	@Autowired
	private MusicListUtils musicListUtil;

	@Autowired
	public PlaylistController(PlaylistService playlistServ, 
		PlaylistSongsService playlistSongServ, UserService userServ,
		MusicListUtils musicListUtil, SongService songServ, FileUtils fileUtil) {
        this.userServ = userServ;
        this.songServ = songServ;
        this.fileUtil = fileUtil;
        this.playlistServ = playlistServ;
        this.musicListUtil = musicListUtil;
        this.playlistSongServ = playlistSongServ;
    }
	public PlaylistController() {}


    // Helper method to save file and return its bytes
    @SuppressWarnings("unused")
	private byte[] saveByteFile(MultipartFile file) throws IOException {
        java.nio.file.Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return Files.readAllBytes(filePath);
    }

	@GetMapping("/playlists")
	public String songIndexPage(Model model, HttpSession session, HttpServletRequest request) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    }    

	    List<Song> allSongs = songServ.getAll();

        // Prepare the music list JSON string using SongUtils
        musicListUtil.setMusicList(model, request);
        musicListUtil.setUsersMusicList(model, request, userId);
		User loggedInUser = userServ.getOne(userId);

	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }

		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("allSongs", allSongs);
		model.addAttribute("allUsers", userServ.findAll());
		return "TrackMedia/showAllPlaylists.jsp";
	}

	@GetMapping("/playlists/{id}")
	public String showOneSong(@PathVariable("id") Long id, Model model,
		HttpSession session, HttpServletRequest request) throws IOException {
	    Long userId = (Long) session.getAttribute("user_id");

	    if (userId == null) {
	        return "redirect:/melodydreams/login";
	    }

	    User loggedInUser = userServ.getOne(userId);
	    if (loggedInUser == null) {
	        return "redirect:/melodydreams/login";
	    }
	    
        // Prepare the music list JSON string using SongUtils
	    Song song = songServ.getOne(id);

	    if (song == null) {
	        // Handle the case where the song with the given id is not found
	        return "redirect:/melodydreams/playlists";
	    }

	    musicListUtil.setMusicList(model, request);
        musicListUtil.setSingleMusicList(model, request, id);
        //musicListUtil.setUsersMusicList(model, request, userId);

        model.addAttribute("oneSong", song);
	    model.addAttribute("loggedInUser", loggedInUser);
	    model.addAttribute("allSongs", songServ.getAll());
	    return "TrackMedia/showOnePlaylist.jsp";
	}

	@GetMapping("/newPlaylist")
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
	    model.addAttribute("loggedInUser", loggedInUser);
	    return "TrackMedia/addNewPlaylist.jsp";
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
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"User not logged in\"}");
	    }

	    User loggedUser = userServ.getOne(userId);
	    Song songToAdd = songServ.getOne(songId);

	    // Handle validation errors
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("\"message\": \"Title is required\"");
	    }
	    
	    try {
	        // Save the playlist and the song
	        Playlist newPlaylist = new Playlist();
	        newPlaylist.setUser(loggedUser);
	        newPlaylist.setTitle(playlist.getTitle());
	        newPlaylist.setDescription(playlist.getDescription());
	        playlistServ.create(newPlaylist);

	        PlaylistSongs newPlaylistSong = new PlaylistSongs();
	        newPlaylistSong.setSong(songToAdd);
	        newPlaylistSong.setUser(loggedUser);
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
	    Song songToAdd = songServ.getOne(songId);

	    // Handle validation errors
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("\"message\": \"Title is required\"");
	    }
	    
	    try {
	        // Save the playlist and the song
	        Playlist newPlaylist = playlistSong.getPlaylist();

	        PlaylistSongs newPlaylistSong = new PlaylistSongs();
	        newPlaylistSong.setSong(songToAdd);
	        newPlaylistSong.setUser(loggedUser);
	        newPlaylistSong.setPlaylist(newPlaylist);
	        playlistSongServ.create(newPlaylistSong);

	        return ResponseEntity.ok("{\"message\": \"Song Added To Playlist Successfully\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"An error occurred\"}");
	    }
	}	

	// Method to display the edit form
    @GetMapping("/editPlaylist/{id}")
    public String displayEditForm(@PathVariable("id") Long id,
    	Model model, HttpSession session, HttpServletRequest request) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/melodydreams/login";
        }

        fileUtil.setUserAttributes(model, userId);
        Song song = songServ.getOne(id);
        if (song == null || !song.getUser().getId().equals(userId)) {
            return "redirect:/melodydreams/playlists"; // or some error handling mechanism
        }

        model.addAttribute("song", song);
	    musicListUtil.setMusicList(model, request);
        musicListUtil.setSingleMusicList(model, request, id);
        musicListUtil.setUsersMusicList(model, request, userId);
        return "TrackMedia/editOneTrack.jsp"; // Adjust the path to your JSP file
    }

    @PatchMapping("/process/editPlaylist/{id}")
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
            return "redirect:/melodydreams/playlists"; // or some error handling mechanism
        }

        try {
            if (!newImageData.isEmpty() || !newAudioData.isEmpty()) {
                // Update and set song data
                fileUtil.updateSongDataAndSetAttributes(songToEdit, newImageData, newAudioData, userId);
            }

            // Call the update method in the service
            songServ.update(songToEdit);

            // Redirect to the track details page after successful update
            return "redirect:/melodydreams/playlists/" + existingSong.getId();
        } catch (IOException e) {
            // Handle file save error
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

	@DeleteMapping("/deletePlaylist/{id}")
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
	        return "redirect:/melodydreams/playlists";
	    }

	    // Redirect to the track listing page after successful deletion
	    return "redirect:/melodydreams/playlists";
	}

}

