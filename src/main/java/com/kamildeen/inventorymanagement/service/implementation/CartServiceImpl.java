package com.kamildeen.inventorymanagement.service.implementation;

import com.kamildeen.inventorymanagement.model.*;
import com.kamildeen.inventorymanagement.repository.CartRepository;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.repository.ProductRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;


    private Product getProduct(String productId){
        Long productID = Long.parseLong(productId);
        return productRepository.findById(productID)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product id " +productId+  " does not exist"));
    }

    private Customer getCustomerByPhone(String phone){
        return customerRepository.findByPhone(phone)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with phone " +phone+  " does not exist"));

    }

    @Override
    public CartResponseDTO addItemToCart(AddToCartDTO addToCartDTO) {

        Product product = getProduct(addToCartDTO.getProductId());
        Customer customer = getCustomerByPhone(addToCartDTO.getCustomerPhone());

        if (product.getAvailableQuantity() < addToCartDTO.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity requested is not available");
        }
        Cart cart = new Cart(new Date(), product, customer, addToCartDTO.getQuantity());
        cartRepository.save(cart);
        List<Cart> cartItems = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        CartResponseDTO resp = new CartResponseDTO(customer, cartItems);
        return resp;
    }

    private static CartItemDTO getDTOFromCart(Cart cart){
        CartItemDTO cartItemDTO = new CartItemDTO(cart);
        return cartItemDTO;
    }

    @Override
    public CartDTO getAllCartItems(String phoneNo) {
        Customer customer = getCustomerByPhone(phoneNo);
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for(Cart cart : cartList){
            CartItemDTO cartItemDTO = getDTOFromCart(cart);
            cartItemDTOList.add(cartItemDTO);
        }

        double totalCost = 0;
        for (CartItemDTO cartItemDTO : cartItemDTOList){
            totalCost += (cartItemDTO.getProduct().getPrice() * cartItemDTO.getQuantity());
        }

        CartDTO cartDTO = new CartDTO(customer, cartItemDTOList, totalCost);
        return cartDTO;
    }

    @Override
    public int updateCartItem(AddToCartDTO addToCartDTO) {

        try {
            Customer customer = getCustomerByPhone(addToCartDTO.getCustomerPhone());
            Product product = getProduct(addToCartDTO.getProductId());
            Cart cart = cartRepository.findByCustomerAndProduct(customer, product);
            cart.setCreatedDate(new Date());
            if(addToCartDTO.getQuantity() > cart.getQuantity()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, we have limited stocks available");
            }
            cart.setQuantity(addToCartDTO.getQuantity());
            cartRepository.save(cart);
            return 1;
        } catch (Exception ex){
            ex.getMessage();
            return 0;
        }
    }

    @Override
    public int deleteCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
        return 1;
    }

    @Override
    public int deleteAllCartItems(String customerPhone) {
        Customer customer = getCustomerByPhone(customerPhone);
        cartRepository.deleteByCustomer(customer);
        return 1;
    }
}
