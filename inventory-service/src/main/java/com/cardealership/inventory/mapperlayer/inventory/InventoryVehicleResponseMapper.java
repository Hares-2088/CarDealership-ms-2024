package com.cardealership.inventory.mapperlayer.inventory;


import com.cardealership.inventory.dataaccesslayer.inventory.Inventory;
import com.cardealership.inventory.presentationlayer.inventory.InventoryVehicleResponseModel;
import com.cardealership.inventory.presentationlayer.vehicle.VehicleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryVehicleResponseMapper {


    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    InventoryVehicleResponseModel entityToResponseModel(Inventory inventory, List<VehicleResponseModel> availableVehicles);
}