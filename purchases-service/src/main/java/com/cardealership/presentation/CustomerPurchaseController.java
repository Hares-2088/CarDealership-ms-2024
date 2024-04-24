package com.cardealership.presentation;

import com.cardealership.business.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers/{customerId}/purchases")
public class CustomerPurchaseController {

    private final SaleService saleService;

    @Autowired
    public CustomerPurchaseController(SaleService saleService) {
        this.saleService = saleService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SaleResponseModel>> getAllPurchases(@PathVariable String customerId) {
        return ResponseEntity.ok().body(saleService.getAllPurchases(customerId));
    }
    @GetMapping(value = "{saleId}", produces = "application/json")
    public ResponseEntity<SaleResponseModel> getCustomerPurchaseBySaleId(@PathVariable String customerId, @PathVariable String saleId) {
        return ResponseEntity.ok().body(saleService.getCustomerPurchaseBySaleId(customerId, saleId));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<SaleResponseModel> createCustomerPurchase(@PathVariable String customerId, @RequestBody SaleRequestModel saleRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.addCustomerPurchase(customerId, saleRequestModel));
    }

    @PutMapping(value = "{saleId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SaleResponseModel> updateCustomerPurchase(@PathVariable String customerId, @PathVariable String saleId, @RequestBody SaleRequestModel saleRequestModel) {
        return ResponseEntity.ok().body(saleService.updateSale(saleRequestModel, saleId, customerId));
    }
}
