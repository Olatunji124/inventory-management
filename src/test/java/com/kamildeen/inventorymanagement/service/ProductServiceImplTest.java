package com.kamildeen.inventorymanagement.service;

import com.kamildeen.inventorymanagement.model.Product;
import com.kamildeen.inventorymanagement.repository.ProductRepository;
import com.kamildeen.inventorymanagement.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;
    private ProductServiceImpl underTest;
    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        underTest = new ProductServiceImpl(productRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void addProduct() {
        //given
        Product product = new Product(null, "X-Guitars", 200, 5.0, "Awesome");

        //when
        underTest.addProduct(product);

        //then
        then(productRepository).should().save(productArgumentCaptor.capture());
        Product productArgumentCaptorValue = productArgumentCaptor.getValue();
        assertThat(productArgumentCaptorValue).isEqualTo(product);
    }

    @Test
    @Disabled
    void getById() {

    }

    @Test
    void getByName() {
        //given
        String name = "HB-Pencil";
        Product product = new Product(null, name, 200, 5.0, "Awesome");


        //when
        productRepository.save(product);
        Product optionalCustomer = underTest.getByName(name);

        //then
        assertThat(optionalCustomer.getName().equals(product.getName()));

    }

    @Test
    void getAllProducts() {
        underTest.getAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    @Disabled
    void updateProduct() {
    }
}