package com.cardealership.customers.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setupDb(){
        customerRepository.deleteAll();
    }

    @Test
    public void whenCustomerExists_ReturnCustomerByCustomerId() {
        //arrange
        Customer customer = new Customer("Adem", "Bessam", "adembessam@gail.com",
                new Address("1234", "Toronto", "ON", "Canada", "M1M1M1"),
                new ArrayList<>());
        customerRepository.save(customer);

        //act
        Customer savedCustomer = customerRepository.findByCustomerIdentifier_CustomerId(customer.getCustomerIdentifier().getCustomerId());

        //assert
        assertNotNull(savedCustomer);
        assertEquals(customer.getCustomerIdentifier(), savedCustomer.getCustomerIdentifier());
        assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(customer.getLastName(), savedCustomer.getLastName());
        assertEquals(customer.getAddress(), savedCustomer.getAddress());
    }
    @Test
    public void whenCustomerDoesNotExist_ReturnNull() {
        //act
        Customer savedCustomer = customerRepository.findByCustomerIdentifier_CustomerId("1234");

        //assert
        assertNull(savedCustomer);
    }
}