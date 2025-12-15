package org.radon.shopyvendor.shared.aop.exceptionHandling;

import org.radon.shopyvendor.shared.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionModel extends RuntimeException {

    public String message;
    public HttpStatus status;

    public ExceptionModel(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public ResponseEntity<ErrorResponse> makeResponse(String path){
        return ResponseEntity.status(this.status).body(new ErrorResponse(
                status.value(),
                status.name(),
                message,
                path
        ));
    }

}
