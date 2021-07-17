package com.kamildeen.inventorymanagement.service;

import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.service.implementation.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.BDDMockito.then;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;
    private CustomerServiceImpl underTest;
    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerServiceImpl(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void addCustomer() {
        // Given a customer
        Customer customer = new Customer(null, "Maryam", "Sofy", "08023412586","sofy@gmail.com", null);

        // ... a request
        CustomerDTO request = new CustomerDTO(customer.getFirstName(),customer.getLastName(),
                customer.getPhone(),customer.getEmail());

        //When
        underTest.addCustomer(request);

        //Then
        then(customerRepository).should().save(customerArgumentCaptor.capture());
        Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
        assertThat(customerArgumentCaptorValue).isEqualTo(customer);
    }
}