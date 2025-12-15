package org.radon.shopyorder.shared.aop.exceptionHandling;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.radon.shopyorder.shared.dto.ErrorResponse;
import org.radon.shopyorder.shared.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionModel extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public ExceptionModel(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }


    public ResponseEntity<Response> makeResponse(String path) {
        return ResponseEntity.status(status).body(
                new Response(
                        null,
                        new ErrorResponse(
                                status.value(),
                                status.name(),
                                message,
                                path
                        )
                )
        );
    }


}
