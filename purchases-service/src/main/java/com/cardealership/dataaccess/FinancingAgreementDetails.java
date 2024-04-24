package com.cardealership.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class FinancingAgreementDetails {

    private Integer numberOfMonthlyPayments;
    private BigDecimal monthlyPaymentAmount;
    private BigDecimal downPaymentAmount;
    private Currency paymentCurrency;
}
