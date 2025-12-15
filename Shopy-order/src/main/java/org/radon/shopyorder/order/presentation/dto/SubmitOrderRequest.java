package org.radon.shopyorder.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderRequest {
    private Long userId;
    private UUID cartId;
}
