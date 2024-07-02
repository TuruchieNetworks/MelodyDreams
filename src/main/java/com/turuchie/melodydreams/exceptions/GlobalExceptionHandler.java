package com.turuchie.melodydreams.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundError(NoHandlerFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Errors/error.jsp"); // Ensure this matches your actual 404 error page path
        modelAndView.addObject("errorMessage", "The page you are looking for is not found.");
        return modelAndView;
    }

    // Additional global exception handlers can be added here
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralError(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Errors/error.jsp"); // Ensure this matches your actual general error page path
        modelAndView.addObject("errorMessage", "An error has occurred. Please try again later.");
        return modelAndView;
    }
}
