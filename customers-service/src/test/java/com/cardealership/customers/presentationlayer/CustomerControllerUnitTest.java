package com.cardealership.customers.presentationlayer;

import com.cardealership.customers.businesslayer.CustomerService;
import com.cardealership.customers.datalayer.CustomerRepository;
import com.cardealership.customers.datalayer.PhoneNumber;
import com.cardealership.customers.datalayer.PhoneType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CustomerController.class)
class CustomerControllerUnitTest {
    private final String FOUND_CUSTOMER_ID = "cus01";
    private final String NOT_FOUND_CUSTOMER_ID = "cus0";
    private final String INVALID_CUSTOMER_ID = "cus00";

    @Autowired
    CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    public void whenNoCustomerExists_thenReturnEmptyList() {
        //arrange
        when(customerService.getCustomers()).thenReturn(Collections.emptyList());

        //act
        ResponseEntity<List<CustomerResponseModel>> responseEntityList = customerController.getCustomers();

        //assert
        assertNotNull(responseEntityList);
        assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
        assertTrue(responseEntityList.getBody().isEmpty());
        verify(customerService, times(1)).getCustomers();
    }

    @Test
    public void whenCustomerExists_thenReturnCustomer(){

        CustomerRequestModel customerRequestModel = buildCustomerRequestModel();
        CustomerResponseModel customerResponseModel = buildCustomerResponseModel();

        //arrange
        when(customerService.addCustomer(customerRequestModel)).thenReturn(customerResponseModel);

        //act
        ResponseEntity<CustomerResponseModel> responseEntity = customerController.addCustomer(customerRequestModel);

        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(customerResponseModel, responseEntity.getBody());
        verify(customerService, times(1)).addCustomer(customerRequestModel);

    }

    private CustomerRequestModel buildCustomerRequestModel() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "1234567890"));
        return CustomerRequestModel.builder()
                .firstName("Adem")
                .lastName("Bessam")
                .emailAddress("adem@gmail.com")
                .streetAddress("1234 Main St")
                .city("Toronto")
                .province("ON")
                .country("Canada")
                .phoneNumbers(phoneNumbers)
                .build();
    }

    private CustomerRequestModel buildCustomerRequestModel(String firstname) {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "1234567890"));
        return CustomerRequestModel.builder()
                .firstName(firstname)
                .lastName("Bessam")
                .emailAddress("adem@gmail.com")
                .streetAddress("1234 Main St")
                .city("Toronto")
                .province("ON")
                .country("Canada")
                .phoneNumbers(phoneNumbers)
                .build();
    }

    private CustomerResponseModel buildCustomerResponseModel() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE, "1234567890"));
        return CustomerResponseModel.builder()
                .customerId(FOUND_CUSTOMER_ID)
                .firstName("Adem")
                .lastName("Bessam")
                .emailAddress("adem@gmail.com")
                .streetAddress("1234 Main St")
                .city("Toronto")
                .province("ON")
                .country("Canada")
                .phoneNumbers(phoneNumbers)
                .build();
    }
}