package itstoony.com.github.shoppingchart.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long id;

    private CustomerDTO customer;

    private Set<CartItemDTO> items = new HashSet<>();
}
