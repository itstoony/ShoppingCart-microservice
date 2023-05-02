package itstoony.com.github.shoppingchart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import itstoony.com.github.shoppingchart.service.CartService;
import itstoony.com.github.shoppingchart.service.CustomerService;
import itstoony.com.github.shoppingchart.service.ProductService;
import itstoony.com.github.shoppingchart.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.List;

import static itstoony.com.github.shoppingchart.utils.Utils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebMvcTest
class CartControllerTest {

    final String CART_API = "/api/cart";

    @Autowired
    MockMvc mvc;

    @MockBean
    CartService cartService;

    @MockBean
    ProductService productService;

    @MockBean
    CustomerService customerService;
    
    @Test
    @DisplayName("Should add an item to the cart")
    void addItemTest() throws Exception {
        // scenery
        CartItemRecord cartItemRecord = createValidProductRecord();
        CartRecord cartRecord = createValidCartRecord(cartItemRecord);

        Cart cart = createValidCart();
        CartItem cartItem = createValidCartItem();
        Customer customer = createValidCustomer();
        Product product = createValidProduct();

        cartItem.setCart(cart);
        cartItem.setProduct(product);

        cart.setItems(new HashSet<>(List.of(cartItem)));
        cart.setCustomer(customer);

        String json = new ObjectMapper().writeValueAsString(cartRecord);


        given(cartService.addItem(any(CartRecord.class))).willReturn(cart);
        
        // execution
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CART_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // validation
        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("customer.id").value(customer.getId()))
                .andExpect(jsonPath("customer.name").value(customer.getName()))
                .andExpect(jsonPath("customer.email").value(customer.getEmail()))
                .andExpect(jsonPath("items[0].id").value(cartItem.getId()))
                .andExpect(jsonPath("items[0].product.id").value(product.getId()))
                .andExpect(jsonPath("items[0].quantity").value(cartItem.getQuantity()));

    }

}
