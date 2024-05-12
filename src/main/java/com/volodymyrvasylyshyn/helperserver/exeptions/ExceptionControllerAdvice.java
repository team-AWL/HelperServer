package com.volodymyrvasylyshyn.helperserver.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public final ResponseEntity<String> handlerCustomException(EmailAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public final ResponseEntity<String> handlerCustomException(EmailNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OldPasswordIsIncorrectException.class)
    public final ResponseEntity<String> handlerCustomException(OldPasswordIsIncorrectException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = OAuth2AuthenticationProcessingException.class)
    public final ResponseEntity<String> handlerCustomException(OAuth2AuthenticationProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = BadRequestException.class)
    public final ResponseEntity<String> handlerCustomException(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UserNotExistException.class)
    public final ResponseEntity<String> handlerCustomException(UserNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AnnouncementNotFoundException.class)
    public final ResponseEntity<String> handlerCustomException(AnnouncementNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ImageNotFoundException.class)
    public final ResponseEntity<String> handlerCustomException(ImageNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AnnouncementAlreadyExistException.class)
    public final ResponseEntity<String> handlerCustomException(AnnouncementAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
