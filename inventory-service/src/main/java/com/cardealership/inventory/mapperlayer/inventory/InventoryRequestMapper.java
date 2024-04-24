package com.cardealership.inventory.mapperlayer.inventory;


import com.cardealership.inventory.dataaccesslayer.inventory.Inventory;
import com.cardealership.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import com.cardealership.inventory.presentationlayer.inventory.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Inventory requestModelToEntity(InventoryRequestModel inventoryRequestModel, InventoryIdentifier inventoryIdentifier);
}
