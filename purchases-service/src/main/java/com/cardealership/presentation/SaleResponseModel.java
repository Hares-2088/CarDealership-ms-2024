package com.cardealership.presentation;


import com.cardealership.dataaccess.Currency;
import com.cardealership.dataaccess.FinancingAgreementDetails;
import com.cardealership.dataaccess.SaleStatus;
import com.cardealership.dataaccess.Warranty;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseModel{

    private String saleId;
    private String inventoryId;
    private String vehicleId;
    private String customerId;
    private String vehicleMake;
    private String vehicleModel;
    private String employeeId;
    private String customerFirstName;
    private String customerLastName;
    private String employeeFirstName;
    private String employeeLastName;
    private BigDecimal salePrice;
    private Currency currency;
    private SaleStatus saleStatus;
    private LocalDate saleOfferDate;
    private FinancingAgreementDetails financingAgreementsDetails;
    private Warranty warranty;
}
