package com.turuchie.melodydreams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.MusicListUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/melodydreams")
public class CountryTimeZone {
    @Value("${file.upload-dir}") // Specify the file upload directory in application.properties
    private String uploadDir;

	@Autowired
	private SongService songServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private MusicListUtils musicListUtil;

	@Autowired
	public CountryTimeZone(UserService userServ, MusicListUtils musicListUtil, SongService songServ) {
        this.userServ = userServ;
        this.songServ = songServ;
        this.musicListUtil = musicListUtil;
    }

	public CountryTimeZone() {}


    // Helper method to save file and return its bytes
    @SuppressWarnings("unused")
	private byte[] saveFile(MultipartFile file) throws IOException {
        java.nio.file.Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return Files.readAllBytes(filePath);
    }

	@GetMapping("/countryTimeZones")
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
		return "CountryCodes/CountryCodes.jsp";
	}

	@GetMapping("/countryTimeZones/{id}")
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
	        return "redirect:/melodydreams/countryTimeZones";
	    }

	    musicListUtil.setMusicList(model, request);
        musicListUtil.setSingleMusicList(model, request, id);
        musicListUtil.setUsersMusicList(model, request, userId);

        model.addAttribute("oneSong", song);
	    model.addAttribute("loggedInUser", loggedInUser);
	    model.addAttribute("allSongs", songServ.getAll());
	    return "CountryCodes/CountryCodes.jsp";
	}

}

