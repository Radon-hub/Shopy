package org.radon.shopyorder.cart.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.radon.shopyorder.cart.domain.model.CartStatus;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCartStatusRequest {
    private UUID cartId;
    private CartStatus cartStatus;
}
