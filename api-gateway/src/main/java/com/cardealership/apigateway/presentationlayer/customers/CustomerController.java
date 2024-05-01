package com.cardealership.apigateway.presentationlayer.customers;

import com.cardealership.apigateway.businesslayer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(
            value = "",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<CustomerResponseModel> createCustomer(@RequestBody CustomerRequestModel customerRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequestModel));
    }

    @PutMapping(
            value = "/{customerId}",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<Void> updateCustomer(@PathVariable String customerId, @RequestBody CustomerRequestModel customerRequestModel) {
        customerService.updateCustomer(customerId, customerRequestModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(
            value = "/{customerId}"
    )
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
