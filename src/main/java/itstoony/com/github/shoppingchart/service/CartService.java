package itstoony.com.github.shoppingchart.service;

import itstoony.com.github.shoppingchart.dto.record.CartRecord;
import itstoony.com.github.shoppingchart.exception.BusinessException;
import itstoony.com.github.shoppingchart.model.Cart;
import itstoony.com.github.shoppingchart.model.CartItem;
import itstoony.com.github.shoppingchart.model.Customer;
import itstoony.com.github.shoppingchart.model.Product;
import itstoony.com.github.shoppingchart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    public Cart addItem(CartRecord dto) {

        if (!customerService.existsById(dto.customerID())) {
            throw new BusinessException("Passed customer doesn't exist");
        }

        dto.items().forEach(carItem -> {
            if (!productService.existsById(carItem.productID())) {
                throw new BusinessException("Invalid Product");
            }


            if (!productService.quantityAvailable(carItem.productID(), carItem.quantity())) {
                throw new BusinessException("Passed product doesn't have storage available");
            }

        });

        Set<CartItem> cartItems = new HashSet<>();

        dto.items().forEach(cartItemRecord -> {
                    Product product = productService.findById(cartItemRecord.productID());
                    CartItem cartItem = new CartItem(null, product, cartItemRecord.quantity(), null);
                    cartItemService.save(cartItem);

                    cartItems.add(cartItem);
                }
        );


        Customer customer = customerService.findById(dto.customerID());

        Cart cart = Cart.builder()
                .id(null)
                .customer(customer)
                .items(cartItems)
                .build();

        cartRepository.save(cart);

        return cart;

    }

}
