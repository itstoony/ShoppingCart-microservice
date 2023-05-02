package itstoony.com.github.shoppingchart.dto.controller;

import itstoony.com.github.shoppingchart.dto.CartDTO;
import itstoony.com.github.shoppingchart.dto.record.CartRecord;
import itstoony.com.github.shoppingchart.model.Cart;
import itstoony.com.github.shoppingchart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final ModelMapper modelMapper;
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> addItem(CartRecord dto) {
        Cart cart = cartService.addItem(dto);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        return ResponseEntity.ok(cartDTO);
    }
}
