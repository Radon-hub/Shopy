package org.radon.shopy.shared.aop.exceptionHandling.model;

import jakarta.servlet.http.HttpServletRequest;
import org.radon.shopy.shared.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception, HttpServletRequest request)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                exception.getMessage(),
                request.getRequestURI()
        ));
    }

    @ExceptionHandler(CredentialException.class)
    public ResponseEntity<ErrorResponse> handleException(CredentialException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorResponse> userExistException(UserExistException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(PasswordCanNotBeSameException.class)
    public ResponseEntity<ErrorResponse> passwordCanNotBeSameException(PasswordCanNotBeSameException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> passwordNotMatchException(PasswordNotMatchException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(PasswordOldNotMatchException.class)
    public ResponseEntity<ErrorResponse> passwordOldNotMatchException(PasswordOldNotMatchException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AddressNotFoundException exception, HttpServletRequest request){
        return exception.makeResponse(request.getRequestURI());
    }
}
