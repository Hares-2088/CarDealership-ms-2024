package com.cardealership.presentation;


import com.cardealership.dataaccess.Currency;
import com.cardealership.dataaccess.FinancingAgreementDetails;
import com.cardealership.dataaccess.SaleStatus;
import com.cardealership.dataaccess.Warranty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SaleRequestModel {

    private String inventoryId;
    private String vehicleId;
    private String employeeId;
    private BigDecimal salePrice;
    private SaleStatus saleStatus;
    private Currency currency;
    private LocalDate saleOfferDate;
    private FinancingAgreementDetails financingAgreementDetails;
    private Warranty warranty;
}
