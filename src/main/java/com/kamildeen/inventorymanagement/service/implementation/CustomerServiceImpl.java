package com.kamildeen.inventorymanagement.service.implementation;

import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerDTO customerDTO) {
        //check if customer already exists
        boolean customerExists = customerRepository.findByPhone(customerDTO.getPhone()).isPresent();
        if(customerExists){
            throw new IllegalStateException("User already exists");
        }

        // save a new customer
        Customer  customer = new Customer(customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getPhone(), customerDTO.getEmail());
        customerRepository.save(customer);
        return "Customer Added Successfully";
    }
}
