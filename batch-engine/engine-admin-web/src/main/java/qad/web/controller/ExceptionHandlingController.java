package qad.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by meng on 7/5/14.
 */
@ControllerAdvice
public class ExceptionHandlingController {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleExceptions(WebRequest request, Exception exception){
        logger.error("Error occured in controller..", exception);
        return null;
    }
}
