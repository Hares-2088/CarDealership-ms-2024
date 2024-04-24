package com.cardealership.customers.presentationlayer;

import com.cardealership.customers.datalayer.CustomerRepository;
import com.cardealership.customers.datalayer.PhoneNumber;
import com.cardealership.customers.datalayer.PhoneType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControllerIntegrationTest {

    private final String BASE_URI_CUSTOMERS = "/api/v1/customers";
    private final String FOUND_CUSTOMER_ID = "cus01";
    private final String FOUND_CUSTOMER_FIRST_NAME = "Adem";
    private final String FOUND_CUSTOMER_LAST_NAME = "Bessam";
    private final String NOT_FOUND_CUSTOMER_ID = "cus00";
    private final String INVALID_CUSTOMER_ID = "cus00";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebTestClient  webTestClient;

    @Test
    public void whenGetCustomers_thenReturnAllCustomers() {
        //arrange
        Long sizeDB = customerRepository.count();

        // act & assert
        webTestClient.get()
                .uri(BASE_URI_CUSTOMERS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                    assertTrue(list.size() == sizeDB);
                });
    }

    @Test
    public void whenGetCustomerDoesNotExist_thenReturnNotFound() {
        // act & assert
        webTestClient.get()
                .uri(BASE_URI_CUSTOMERS + "/" + NOT_FOUND_CUSTOMER_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown customerId: " + NOT_FOUND_CUSTOMER_ID);
    }

    @Test
    public void whenValidCustomer_thenCreateCustomer() {

        //arrange
        long sizeDB = customerRepository.count();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(PhoneType.HOME,"123-456-7890"));
        phoneNumbers.add(new PhoneNumber(PhoneType.MOBILE,"123-456-7890"));
        CustomerRequestModel customerRequestModel = new CustomerRequestModel("Adem", "Bessam", "adem@gmail.com",
                "1234 Main St", "Apt 1", "New York", "NY", "12345", phoneNumbers);

        webTestClient.post()
                .uri(BASE_URI_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(customerRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerResponseModel.class)
                .value((customerResponseModel) -> {
                    assertNotNull(customerResponseModel);
                    assertEquals(customerRequestModel.getFirstName(), customerResponseModel.getFirstName());
                    assertEquals(customerRequestModel.getLastName(), customerResponseModel.getLastName());
                    assertEquals(customerRequestModel.getEmailAddress(), customerResponseModel.getEmailAddress());
                    assertEquals(customerRequestModel.getStreetAddress(), customerResponseModel.getStreetAddress());
                    assertEquals(customerRequestModel.getCity(), customerResponseModel.getCity());
                    assertEquals(customerRequestModel.getProvince(), customerResponseModel.getProvince());
                });
        assertEquals(sizeDB + 1, customerRepository.count());
    }
}