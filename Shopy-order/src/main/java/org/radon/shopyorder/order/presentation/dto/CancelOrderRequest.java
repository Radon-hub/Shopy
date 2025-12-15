package org.radon.shopyorder.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderRequest {
    private String orderNumber;
    private Long userId;
}
