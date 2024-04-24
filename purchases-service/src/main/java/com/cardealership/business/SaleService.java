package com.cardealership.business;



import com.cardealership.presentation.SaleRequestModel;
import com.cardealership.presentation.SaleResponseModel;

import java.util.List;

public interface SaleService {

    List<SaleResponseModel> getAllPurchases(String customerId);
    SaleResponseModel getCustomerPurchaseBySaleId(String customerId, String saleId);
    SaleResponseModel addCustomerPurchase(String customerId, SaleRequestModel saleRequestModel);
    SaleResponseModel updateSale(SaleRequestModel saleRequestModel, String saleId, String customerId);

    void deleteSale(String saleId, String customerId);
}
