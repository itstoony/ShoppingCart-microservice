package itstoony.com.github.shoppingchart.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    private Long id;

    private Product product;

    private Integer quantity;

    private Cart cart;

}
