package com.kamildeen.inventorymanagement.service;

import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerDTO customerDTO) {
        Customer  customer = new Customer(customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getPhone(), customerDTO.getEmail());
        customerRepository.save(customer);
        return "Customer Added Successfully";
    }
}
