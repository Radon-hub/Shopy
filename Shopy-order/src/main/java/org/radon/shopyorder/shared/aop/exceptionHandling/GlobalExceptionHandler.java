package org.radon.shopyorder.shared.aop.exceptionHandling;

import jakarta.servlet.http.HttpServletRequest;
import org.radon.shopyorder.shared.dto.ErrorResponse;
import org.radon.shopyorder.shared.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    public GlobalExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(CartAccessDeniedException.class)
    public ResponseEntity<Response> handleException(CartAccessDeniedException ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Response> handleException(CartNotFoundException ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartExistException.class)
    public ResponseEntity<Response> handleException(CartExistException ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<Response> handleException(CartItemNotFoundException ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(NoCartFoundException.class)
    public ResponseEntity<Response> handleException(NoCartFoundException ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartIsAbandoned.class)
    public ResponseEntity<Response> handleException(CartIsAbandoned ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartIsEmpty.class)
    public ResponseEntity<Response> handleException(CartIsEmpty ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(CartNotBelongToUser.class)
    public ResponseEntity<Response> handleException(CartNotBelongToUser ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<Response> handleException(OrderNotFound ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(SubmittedOrderConflict.class)
    public ResponseEntity<Response> handleException(SubmittedOrderConflict ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(OrderNotReadyForPayment.class)
    public ResponseEntity<Response> handleException(OrderNotReadyForPayment ex) {
        return ex.makeResponse(request.getRequestURI());
    }

    @ExceptionHandler(IncorrectPaymentID.class)
    public ResponseEntity<Response> handleException(IncorrectPaymentID ex) {
        return ex.makeResponse(request.getRequestURI());
    }

}
