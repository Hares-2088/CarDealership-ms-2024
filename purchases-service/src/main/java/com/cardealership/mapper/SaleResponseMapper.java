package com.cardealership.mapper;


import com.cardealership.dataaccess.Sale;
import com.cardealership.presentation.CustomerPurchaseController;
import com.cardealership.presentation.SaleResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "Spring")
public interface SaleResponseMapper {

    @Mapping(expression = "java(sale.getSaleIdentifier().getSaleId())", target = "saleId")
    @Mapping(expression = "java(sale.getSalePrice().getAmount())", target = "salePrice")
    @Mapping(expression = "java(sale.getSalePrice().getCurrency())", target = "currency")
    @Mapping(expression = "java(sale.getVehicleModel().getVehicleId())", target = "vehicleId")
    @Mapping(expression = "java(sale.getVehicleModel().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(sale.getVehicleModel().getMake())", target = "vehicleMake")
    @Mapping(expression = "java(sale.getVehicleModel().getModel())", target = "vehicleModel")
    @Mapping(expression = "java(sale.getCustomerModel().getCustomerId())", target = "customerId")
    @Mapping(expression = "java(sale.getCustomerModel().getFirstName())", target = "customerFirstName")
    @Mapping(expression = "java(sale.getCustomerModel().getLastName())", target = "customerLastName")
    @Mapping(expression = "java(sale.getEmployeeModel().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(sale.getEmployeeModel().getFirstName())", target = "employeeFirstName")
    @Mapping(expression = "java(sale.getEmployeeModel().getLastName())", target = "employeeLastName")
    SaleResponseModel entityToResponseModel(Sale sale);

    List<SaleResponseModel> entitiesToListResponseModel(List<Sale> sales);

}
