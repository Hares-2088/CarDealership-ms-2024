package com.cardealership.dataaccess;


import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Sale {

    @Id
    private String id;

    @Indexed(unique = true)
    private SaleIdentifier saleIdentifier;
    private CustomerModel customerModel;
    private VehicleModel vehicleModel;
    private EmployeeModel employeeModel;
    private Price salePrice;
    private SaleStatus saleStatus;
    private FinancingAgreementDetails financingAgreementsDetails;
    private Warranty warranty;
    private LocalDate saleOfferDate;

    public Sale(CustomerModel customerModel, VehicleModel vehicleModel, EmployeeModel employeeModel, Price salePrice, SaleStatus saleStatus, FinancingAgreementDetails financingAgreementsDetails, Warranty warranty, LocalDate saleOfferDate) {
        this.saleIdentifier = new SaleIdentifier();
        this.customerModel = customerModel;
        this.vehicleModel = vehicleModel;
        this.employeeModel = employeeModel;
        this.salePrice = salePrice;
        this.saleStatus = saleStatus;
        this.financingAgreementsDetails = financingAgreementsDetails;
        this.warranty = warranty;
        this.saleOfferDate = saleOfferDate;
    }
}
