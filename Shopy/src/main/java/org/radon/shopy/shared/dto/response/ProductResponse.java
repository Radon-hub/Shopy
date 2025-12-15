package org.radon.shopy.shared.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String title;
    private String description;
    private String price;
    private Integer stock;
    private boolean isInStock;
}
