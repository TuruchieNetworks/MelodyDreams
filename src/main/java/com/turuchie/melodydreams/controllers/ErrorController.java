package com.turuchie.melodydreams.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;
import com.turuchie.melodydreams.utils.ArtistsUtils;
import com.turuchie.melodydreams.utils.DateUtil;
import com.turuchie.melodydreams.utils.FileUtils;
import com.turuchie.melodydreams.utils.MetricsUtil;
import com.turuchie.melodydreams.utils.MusicListUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/melodydreams")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UserService userServ;

	public ErrorController(UserService userServ, MetricsUtil metricsUtil, DateUtil dateUtil,
		MusicListUtils musicListUtil,SongService songServ, FileUtils fileUtil, ArtistsUtils artistUtil) {
        this.userServ = userServ;
        this.dateUtil = dateUtil;
    }
	
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null){
	    	return "redirect:/melodydreams/login";
	    } 

        // Get error status code
        User loggedInUser = userServ.getOne(userId);
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMsg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        // Handle different error status codes
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Set formatted date attributes in the model
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Pass error code and message to the model
                dateUtil.addCurrentDateAttributes(model);
                model.addAttribute("statusCode", statusCode);
                model.addAttribute("errorMessage", errorMsg);
                return "Errors/error.jsp"; // Show custom 404 page
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Pass error code and message to the model
                dateUtil.addCurrentDateAttributes(model);
                model.addAttribute("statusCode", statusCode);
                model.addAttribute("errorMessage", errorMsg);
                return "Errors/error.jsp"; // Show custom 500 page
            }
        }

        dateUtil.addCurrentDateAttributes(model);
        model.addAttribute("loggedInUser", loggedInUser);
        return "melodydreams/error";  //Default error page
        //return "error/general"; // Default error page
    }

    public String getErrorPath() {
        return "/error";
    }
}
