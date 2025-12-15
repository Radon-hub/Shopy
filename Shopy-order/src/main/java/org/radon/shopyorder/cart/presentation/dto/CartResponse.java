package org.radon.shopyorder.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyorder.cart.domain.model.CartStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private UUID id;
    private Long userId;
    private CartStatus cartStatus;
    private List<CartItemResponse> items;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
