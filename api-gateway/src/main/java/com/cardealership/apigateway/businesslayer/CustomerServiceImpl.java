package com.cardealership.apigateway.businesslayer;

import com.cardealership.apigateway.domainclientlayer.customer.CustomerServiceClient;
import com.cardealership.apigateway.mapper.customer.CustomerResponseMapper;
import com.cardealership.apigateway.presentationlayer.customers.CustomerResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerServiceClient customerServiceClient;

    private final CustomerResponseMapper customerResponseMapper;

    public CustomerServiceImpl(CustomerServiceClient customerServiceClient, CustomerResponseMapper customerResponseMapper) {
        this.customerServiceClient = customerServiceClient;
        this.customerResponseMapper = customerResponseMapper;
    }


    @Override
    public List<CustomerResponseModel> getAllCustomers() {
        return customerResponseMapper.responseModelListToResponseModelList(customerServiceClient.getAllCustomers());
    }

    @Override
    public CustomerResponseModel getCustomerByCustomerId(String customerId) {
        return customerResponseMapper.responseModelToResponseModel(customerServiceClient.getCustomerByCustomerId(customerId));
    }
}
