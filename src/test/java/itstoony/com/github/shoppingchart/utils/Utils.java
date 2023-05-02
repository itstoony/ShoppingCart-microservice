package itstoony.com.github.shoppingchart.utils;

import itstoony.com.github.shoppingchart.dto.CartDTO;
import itstoony.com.github.shoppingchart.dto.CartItemDTO;
import itstoony.com.github.shoppingchart.dto.CustomerDTO;
import itstoony.com.github.shoppingchart.dto.ProductDTO;
import itstoony.com.github.shoppingchart.dto.record.CartRecord;
import itstoony.com.github.shoppingchart.dto.record.CartItemRecord;
import itstoony.com.github.shoppingchart.model.Cart;
import itstoony.com.github.shoppingchart.model.CartItem;
import itstoony.com.github.shoppingchart.model.Customer;
import itstoony.com.github.shoppingchart.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Utils {

    public static CartItemRecord createValidProductRecord() {
        return new CartItemRecord(1L, 15);
    }
    public static CartRecord createValidCartRecord(CartItemRecord cartItemRecord) {
        return new CartRecord(1L, List.of(cartItemRecord));
    }

    public static Cart createValidCart() {
        return Cart.builder()
                .id(1L)
                .customer(null)
                .items(new HashSet<>())
                .build();
    }

    public static CartItem createValidCartItem() {
        return CartItem.builder()
                .id(1L)
                .product(null)
                .quantity(15)
                .cart(null)
                .build();
    }

    public static Customer createValidCustomer() {
        return Customer.builder()
                .id(1L)
                .name("Fulano")
                .email("fulano@email.com")
                .password("12345678")
                .build();
    }

    public static Product createValidProduct() {
        return Product.builder()
                .id(1L)
                .name("Product's name")
                .productValue(new BigDecimal("10"))
                .build();
    }

    public static CartDTO createValidCartDTO(CustomerDTO customer, CartItemDTO cartItem) {
        return CartDTO.builder()
                .id(1L)
                .customer(customer)
                .items(new HashSet<>(Collections.singletonList(cartItem)))
                .build();
    }


    public static CartItemDTO createValidCartItemDTO() {
        return CartItemDTO.builder()
                .id(1L)
                .quantity(15)
                .build();
    }


    public static CustomerDTO createValidCustomerDTO() {
        return CustomerDTO.builder()
                .id(1L)
                .name("Fulano")
                .email("fulano@email.com")
                .build();
    }

    public static ProductDTO createValidProductDTO() {
        return ProductDTO.builder()
                .id(1L)
                .name("Product's name")
                .productValue(new BigDecimal("10"))
                .build();
    }

    public static CartItemRecord createValidCartItemRecord() {
        return new CartItemRecord(1L, 15);
    }
}
