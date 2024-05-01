package com.cardealership.apigateway.domainclientlayer.customer;

import com.cardealership.apigateway.mapper.customer.CustomerResponseMapperImpl;
import com.cardealership.apigateway.presentationlayer.customers.CustomerRequestModel;
import com.cardealership.apigateway.presentationlayer.customers.CustomerResponseModel;
import com.cardealership.apigateway.utils.exceptions.HttpErrorInfo;
import com.cardealership.apigateway.utils.exceptions.InvalidInputException;
import com.cardealership.apigateway.utils.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;


@Component
@Slf4j
public class CustomerServiceClient {
    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final String CUSTOMER_SERVICE_BASE_URL;
    private final CustomerResponseMapperImpl customerResponseMapperImpl;

    private CustomerServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                  @Value("${app.customers-service.host}") String customerServiceHost,
                                  @Value("${app.customers-service.port}") String customerServicePort, CustomerResponseMapperImpl customerResponseMapperImpl) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        CUSTOMER_SERVICE_BASE_URL = "http://" + customerServiceHost + ":" + customerServicePort + "/api/v1/customers";
        this.customerResponseMapperImpl = customerResponseMapperImpl;
    }

    public List<CustomerResponseModel> getAllCustomers() {
        try {

            CustomerResponseModel[] customerResponseModels = restTemplate.getForObject(CUSTOMER_SERVICE_BASE_URL, CustomerResponseModel[].class);

            assert customerResponseModels != null;
            return Arrays.asList(customerResponseModels);

        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public CustomerResponseModel getCustomerByCustomerId(String customerId) {
        try {
            String url = CUSTOMER_SERVICE_BASE_URL + "/" + customerId;

            return restTemplate.getForObject(url, CustomerResponseModel.class);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    //post method
    public CustomerResponseModel createCustomer(CustomerRequestModel customerRequestModel) {
        try {
            return restTemplate.postForObject(CUSTOMER_SERVICE_BASE_URL, customerRequestModel, CustomerResponseModel.class);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    //put method
    public void updateCustomer(String customerId, CustomerRequestModel customerRequestModel) {
        try {
            String url = CUSTOMER_SERVICE_BASE_URL + "/" + customerId;
            restTemplate.put(url, customerRequestModel);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }


    //delete method
    public void deleteCustomer(String customerId) {
        try {
            String url = CUSTOMER_SERVICE_BASE_URL + "/" + customerId;
            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        //include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }
}
