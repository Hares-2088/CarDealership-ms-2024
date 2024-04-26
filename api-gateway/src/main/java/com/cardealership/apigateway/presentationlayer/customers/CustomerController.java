package com.cardealership.apigateway.presentationlayer.customers;

import com.cardealership.apigateway.businesslayer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(
            value = "",
            produces = "application/json"
    )
    public ResponseEntity<List<CustomerResponseModel>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping(
            value = "/{customerId}",
            produces = "application/json"
    )
    public ResponseEntity<CustomerResponseModel> getCustomerByCustomerId( @PathVariable String customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerByCustomerId(customerId));
    }
}
