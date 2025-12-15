package org.radon.shopyvendor.shared.aop.exceptionHandling;


import jakarta.servlet.http.HttpServletRequest;
import org.radon.shopyvendor.shared.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoProductFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoProductFoundException(NoProductFoundException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(VendorExistsException.class)
    public ResponseEntity<ErrorResponse> handleVendorExistsException(VendorExistsException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVendorNotFoundException(VendorNotFoundException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(VendorRegisterArgumentException.class)
    public ResponseEntity<ErrorResponse> handleVendorRegisterArgumentException(VendorRegisterArgumentException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(EmptyPaginationException.class)
    public ResponseEntity<ErrorResponse> handleEmptyPaginationException(EmptyPaginationException ex, HttpServletRequest request) {
        return ex.makeResponse(request.getRequestURI());
    }

}
