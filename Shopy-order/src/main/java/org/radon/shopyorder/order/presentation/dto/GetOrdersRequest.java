package org.radon.shopyorder.order.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyorder.order.domain.model.OrderStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersRequest {
    private Long userId;
    private List<OrderStatus> statusList;
}
