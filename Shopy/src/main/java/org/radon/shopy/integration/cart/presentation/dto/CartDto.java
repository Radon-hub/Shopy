package org.radon.shopy.integration.cart.presentation.dto;

import lombok.*;
import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.model.CartStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private UUID id;
    private Long userId;
    private CartStatus cartStatus;
    private List<CartItemDto> items;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
