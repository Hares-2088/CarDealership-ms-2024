package com.cardealership.presentation;

import com.cardealership.dataaccess.Sale;
import com.cardealership.dataaccess.SaleRepository;
import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class SaleControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;
    private ObjectMapper mapper = new ObjectMapper();

    private final String CUSTOMER_BASE_URL = "http://localhost:7001/api/v1/customers";
    private final String  SALE_BASE_URL = "http://localhost:7001/api/v1/customers";


    @BeforeEach
    void init() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void whenGetSAleById_thenReturnsSale() throws JsonProcessingException, URISyntaxException {
        // Arrange
        var customerModel = CustomerModel.builder()
                .customerId("cus01")
                .firstName("John")
                .lastName("Doe")
                .build();

        mockRestServiceServer.expect(ExpectedCount.once(),
                requestTo(new URI(CUSTOMER_BASE_URL + "/cus01")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(customerModel))
                );

        //find the sale by customer id
        List<Sale> sales = saleRepository.findSalesByCustomerModel_CustomerId("cus01");

        Sale sale = sales.stream()
                .filter(s -> s.getVehicleModel().getVehicleId().equals("veh01"))
                .findFirst().get();

        assertNotNull(sale);

        String url = SALE_BASE_URL + "/" + customerModel.getCustomerId() + "/purchases/" + sale.getSaleIdentifier().getSaleId();

        // Act
        webTestClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(SaleResponseModel.class)
                .value((response) -> {
                    assertNotNull(response);
                    assertEquals(sale.getSaleIdentifier().getSaleId(), response.getSaleId());
                    assertEquals(sale.getSalePrice().getAmount(), response.getSalePrice());
                    assertEquals(sale.getSalePrice().getCurrency(), response.getCurrency());
                    assertEquals(sale.getVehicleModel().getVehicleId(), response.getVehicleId());
                    assertEquals(sale.getVehicleModel().getInventoryId(), response.getInventoryId());
                    assertEquals(sale.getVehicleModel().getMake(), response.getVehicleMake());
                    assertEquals(sale.getVehicleModel().getModel(), response.getVehicleModel());
                });


        // Assert
    }
}