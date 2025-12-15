package org.radon.shopyorder.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private T data;
    private ErrorResponse error;

    public Response(T data) {
        this.data = data;
        this.error = null;
    }

}
