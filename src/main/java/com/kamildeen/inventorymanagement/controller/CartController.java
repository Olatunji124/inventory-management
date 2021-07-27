package com.kamildeen.inventorymanagement.controller;

import com.kamildeen.inventorymanagement.model.AddToCartDTO;
import com.kamildeen.inventorymanagement.model.Cart;
import com.kamildeen.inventorymanagement.model.CartDTO;
import com.kamildeen.inventorymanagement.model.CartResponseDTO;
import com.kamildeen.inventorymanagement.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/inventory-management/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add-item")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestBody AddToCartDTO addToCartDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addItemToCart(addToCartDTO));
    }

    @GetMapping("/get-cart-items/{phone}")
    public ResponseEntity<CartDTO> getAllCartItems(@PathVariable("phone") String phone) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllCartItems(phone));
    }

    @DeleteMapping("/delete-cart-item/{id}")
    public ResponseEntity<Integer> deleteCartItem(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteCartItem(id));
    }

    @DeleteMapping("/delete-cart-items/{id}")
    public ResponseEntity<Integer> deleteAllCartItems(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.deleteCartItem(id));
    }

}
