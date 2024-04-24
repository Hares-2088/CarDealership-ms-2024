package com.cardealership.dataaccess;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SaleRepository extends MongoRepository<Sale,String> {

    List<Sale> findSalesByCustomerModel_CustomerId(String customerId);

    Sale findSaleByCustomerModel_CustomerIdAndSaleIdentifier_SaleId(String customerId, String saleId);
}
