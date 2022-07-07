package uz.isystem.universitysystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(NotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> existException(AlreadyExistException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
