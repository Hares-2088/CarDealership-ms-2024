package com.cardealership.business;

import com.cardealership.dataaccess.*;
import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.customer.CustomerServiceClient;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.employee.EmployeeServiceClient;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import com.cardealership.domainclientlayer.inventories.VehicleServiceClient;
import com.cardealership.presentation.SaleRequestModel;
import com.cardealership.presentation.SaleResponseModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration")
class SaleServiceUnitTest {

    @Autowired
    SaleService saleService;

    @MockBean
    CustomerServiceClient customerServiceClient;

    @MockBean
    EmployeeServiceClient employeeServiceClient;

    @MockBean
    VehicleServiceClient vehicleServiceClient;

    @MockBean
    SaleRepository saleRepository;

    @SpyBean
    SaleResponseModel saleResponseModel;


    //Test the post method
    @Test
    public void whenPostSale_thenReturnsSale() {
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

        var saleRequestModel = SaleRequestModel.builder()
                .vehicleId("veh01")
                .inventoryId("inv01")
                .employeeId("emp01")
                .currency(Currency.USD)
                .saleStatus(SaleStatus.PURCHASE_COMPLETED)
                .salePrice(new BigDecimal(20000))
                .saleOfferDate(LocalDate.of(2024, 1, 1))
                .financingAgreementDetails(financingAgreement)
                .warranty(new Warranty(LocalDate.of(2024, 1, 1), "Warranty 1"))
                .build();

        // Create a Sale object based on the SaleRequestModel
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

        when(customerServiceClient.getCustomerByCustomerId("cus01")).thenReturn(customerModel);
        when(employeeServiceClient.getEmployeeByEmployeeId("emp01")).thenReturn(employeeModel);
        when(vehicleServiceClient.getVehicleByInventoryIdAndVehicleId("inv01", "veh01")).thenReturn(vehicleModel);
        when(saleRepository.save(any(Sale.class))).thenReturn(sale1);

        // Act
        SaleResponseModel result = saleService.addCustomerPurchase("cus01", saleRequestModel);

        // Assert
        assertNotNull(result);
        assertEquals(sale1.getSaleIdentifier().getSaleId(), result.getSaleId());

        assertEquals(sale1.getVehicleModel().getVehicleId(), result.getVehicleId());
        assertEquals(sale1.getVehicleModel().getModel(), result.getVehicleModel());
        assertEquals(sale1.getVehicleModel().getInventoryId(), result.getInventoryId());

        assertEquals(sale1.getCustomerModel().getCustomerId(), result.getCustomerId());
        assertEquals(sale1.getCustomerModel().getFirstName(), result.getCustomerFirstName());
        assertEquals(sale1.getCustomerModel().getLastName(), result.getCustomerLastName());

        assertEquals(sale1.getEmployeeModel().getEmployeeId(), result.getEmployeeId());
        assertEquals(sale1.getEmployeeModel().getFirstName(), result.getEmployeeFirstName());
        assertEquals(sale1.getEmployeeModel().getLastName(), result.getEmployeeLastName());
    }
}