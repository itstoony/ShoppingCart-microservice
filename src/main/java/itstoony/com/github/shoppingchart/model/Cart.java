package itstoony.com.github.shoppingchart.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    private Long id;

    private Customer customer;

    private Set<CartItem> items = new HashSet<>();

}
