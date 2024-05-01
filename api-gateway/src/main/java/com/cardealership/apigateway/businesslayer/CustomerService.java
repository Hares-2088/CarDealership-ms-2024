package com.cardealership.apigateway.businesslayer;

import com.cardealership.apigateway.presentationlayer.customers.CustomerRequestModel;
import com.cardealership.apigateway.presentationlayer.customers.CustomerResponseModel;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseModel> getAllCustomers();
    CustomerResponseModel getCustomerByCustomerId(String customerId);
    CustomerResponseModel createCustomer(CustomerRequestModel customerRequestModel);
    void updateCustomer(String customerId, CustomerRequestModel customerRequestModel);
    void deleteCustomer(String customerId);
}
