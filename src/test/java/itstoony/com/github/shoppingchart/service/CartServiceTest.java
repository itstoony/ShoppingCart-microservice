package itstoony.com.github.shoppingchart.service;

import itstoony.com.github.shoppingchart.dto.record.CartItemRecord;
import itstoony.com.github.shoppingchart.dto.record.CartRecord;
import itstoony.com.github.shoppingchart.model.Cart;
import itstoony.com.github.shoppingchart.model.Customer;
import itstoony.com.github.shoppingchart.model.Product;
import itstoony.com.github.shoppingchart.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static itstoony.com.github.shoppingchart.utils.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CartServiceTest {

    CartService cartService;

    @MockBean
    ProductService productService;

    @MockBean
    CustomerService customerService;

    @MockBean
    CartRepository cartRepository;

    @MockBean
    CartItemService cartItemService;

    @BeforeEach
    void setUp() {
        this.cartService = new CartService(productService, customerService, cartRepository, cartItemService);
    }

    @Test
    @DisplayName("Should add an item to an customer's cart")
    void addItemTest() {
        // scenery
        CartItemRecord cartItemRecord = createValidCartItemRecord();
        CartRecord cartRecord = createValidCartRecord(cartItemRecord);

        Product product = createValidProduct();
        Customer customer = createValidCustomer();

        given(productService.existsById(anyLong())).willReturn(true);
        given(productService.findById(cartItemRecord.productID())).willReturn(product);
        given(productService.quantityAvailable(anyLong(), anyInt())).willReturn(true);

        given(customerService.existsById(anyLong())).willReturn(true);
        given(customerService.findById(anyLong())).willReturn(customer);

        // execution
        Cart cart = cartService.addItem(cartRecord);

        // validation
        assertThat(cart.getCustomer()).isEqualTo(customer);
        assertThat(cart
                .getItems()
                .stream()
                .findFirst()
                .get()
                .getProduct()).isEqualTo(product);

        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(productService, times(1)).existsById(anyLong());
        verify(productService, times(1)).quantityAvailable(anyLong(), anyInt());
        verify(customerService, times(1)).existsById(anyLong());

    }

}
