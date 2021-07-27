package com.kamildeen.inventorymanagement.service.implementation;

import com.kamildeen.inventorymanagement.model.*;
import com.kamildeen.inventorymanagement.reporting.service.ReportConsumer;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.repository.OrderRepository;
import com.kamildeen.inventorymanagement.repository.ProductRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import com.kamildeen.inventorymanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderItemServiceImpl orderItemService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private Customer getCustomerByPhone(String phone){
        return customerRepository.findByPhone(phone)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with phone " +phone+  " does not exist"));
    }

    public ProductOrder saveOrder(PlaceOrderDTO orderDTO){
        ProductOrder order = getOrderFromDTO(orderDTO);
        return orderRepository.save(order);
    }

    private ProductOrder getOrderFromDTO(PlaceOrderDTO placeOrderDTO) {
        return new ProductOrder(placeOrderDTO.getCustomer(), placeOrderDTO.getTotalAmount());
    }

    public String placeOrder(String customerPhone) {
        CartDTO cartDTO = cartService.getAllCartItems(customerPhone);
        Customer customer = getCustomerByPhone(customerPhone);
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setCustomer(customer);
        placeOrderDTO.setTotalAmount(cartDTO.getTotalAmount());

        ProductOrder newOrder = saveOrder(placeOrderDTO);
        ReportConsumer.saveOrderItems(newOrder, customerPhone, cartDTO, orderItemService, cartService, productRepository);
        return "Order Placed";
    }

    public ProductOrder getOrder(Long orderId) {

            Optional<ProductOrder> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                return order.get();
            }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
    }

    public List<ProductOrder> listOrders(String customerPhone) {
        Customer customer = getCustomerByPhone(customerPhone);
        List<ProductOrder> orderList = orderRepository.findAllByCustomerOrderByOrderDateDesc(customer);
        return orderList;
    }

    private static OrderItemDTO getOrderFromDTO(ProductOrder productOrder) {
        return new OrderItemDTO(productOrder);
    }


}

// List<OrderItemDTO> list = new ArrayList<>();
//for(ProductOrder productOrder : orderList){
//            OrderItemDTO itemDTO = new
//                    //getOrderFromDTO(productOrder);
//            list.add(itemDTO);
//        }
//        return list;