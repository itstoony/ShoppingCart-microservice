package itstoony.com.github.shoppingchart.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {

    private Long id;

    private ProductDTO product;

    private Integer quantity;

}
