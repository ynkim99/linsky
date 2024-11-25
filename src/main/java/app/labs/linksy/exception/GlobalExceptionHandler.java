package app.labs.linksy.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SessionExpiredException.class)
    public String handleSessionExpired(SessionExpiredException e, HttpServletResponse response) {
        return "redirect:/login";
    }
} 