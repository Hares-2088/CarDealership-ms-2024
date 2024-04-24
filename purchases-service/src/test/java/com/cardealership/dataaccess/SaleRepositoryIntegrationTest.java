package com.cardealership.dataaccess;

import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class SaleRepositoryIntegrationTest {

    @Autowired
    private SaleRepository saleRepository;

    @Test
    public void whenCustomerIdIsValid_thenReturnsSales() {
        // Arrange
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

        var sale2 = Sale.builder()
                .saleIdentifier(new SaleIdentifier())
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
        saleRepository.save(sale2);


        //Act
        List<Sale> sales = saleRepository.findSalesByCustomerModel_CustomerId("cus01");

        //Assert
        assertNotNull(sales);
        assertEquals(2, sales.size());
    }
}