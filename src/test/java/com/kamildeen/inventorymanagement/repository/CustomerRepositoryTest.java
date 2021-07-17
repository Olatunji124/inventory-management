package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Test
    void canFindByPhone() {
        //given
        String phoneNumber = "09020304050";
        Customer customer = new Customer(null,"Ahmad", "Uche",phoneNumber,
                "ahmad@gmail.com",null);
        underTest.save(customer);

        //when

        Optional<Customer> optionalCustomer = underTest.findByPhone(phoneNumber);
        assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c).isEqualTo(customer);
                });

    }
}