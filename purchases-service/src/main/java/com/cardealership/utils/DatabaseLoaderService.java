package com.cardealership.utils;

import com.cardealership.dataaccess.*;
import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DatabaseLoaderService implements CommandLineRunner {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public void run(String... args) throws Exception {

        var saleIdentifier = new SaleIdentifier();
        var vehicleModel = VehicleModel.builder()
                .vehicleId("veh01")
                .model("Toyota")
                .model("Camry")
                .inventoryId("inv01")
                .build();

        var customerModel = CustomerModel.builder()
                .customerId("cus01")
                .firstName("John")
                .lastName("Doe")
                .build();

        var employeeModel = EmployeeModel.builder()
                .employeeId("emp01")
                .firstName("John")
                .lastName("Doe")
                .build();

        var financingAgreement = FinancingAgreementDetails.builder()
                .numberOfMonthlyPayments(60)
                .monthlyPaymentAmount(new BigDecimal(500))
                .downPaymentAmount(new BigDecimal(1000))
                .paymentCurrency(Currency.USD)
                .build();

        var sale1 = Sale.builder()
                .saleIdentifier(saleIdentifier)
                .vehicleModel(vehicleModel)
                .customerModel(customerModel)
                .employeeModel(employeeModel)
                .financingAgreementsDetails(financingAgreement)
                .salePrice(new Price(new BigDecimal(20000), Currency.USD))
                .saleStatus(SaleStatus.PURCHASE_COMPLETED)
                .saleOfferDate(LocalDate.of(2024, 1, 1))
                .warranty(new Warranty(LocalDate.of(2024, 1, 1), "Warranty 1"))
                .build();

        saleRepository.save(sale1);
    }
}
