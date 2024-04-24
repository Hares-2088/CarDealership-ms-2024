package com.cardealership.mapper;


import com.cardealership.dataaccess.FinancingAgreementDetails;
import com.cardealership.dataaccess.Price;
import com.cardealership.dataaccess.Sale;
import com.cardealership.dataaccess.SaleIdentifier;
import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import com.cardealership.presentation.SaleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(expression = "java(price)", target = "salePrice")
    Sale requestModelToEntity(SaleRequestModel saleRequestModel, SaleIdentifier saleIdentifier,
                              VehicleModel vehicleModel, EmployeeModel employeeModel, CustomerModel customerModel,
                              Price price);
}
