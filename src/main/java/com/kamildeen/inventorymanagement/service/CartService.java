package com.kamildeen.inventorymanagement.service;

import com.kamildeen.inventorymanagement.model.*;

import java.util.List;

public interface CartService {

    CartResponseDTO addItemToCart(AddToCartDTO addToCartDTO);
    CartDTO getAllCartItems(String phoneNo);
    int updateCartItem(AddToCartDTO addToCartDTO);
    int deleteCartItem(Long cartId);
    int  deleteAllCartItems(String customerPhone);

}
