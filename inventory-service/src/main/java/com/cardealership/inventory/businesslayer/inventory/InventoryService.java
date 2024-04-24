package com.cardealership.inventory.businesslayer.inventory;




import com.cardealership.inventory.presentationlayer.inventory.InventoryRequestModel;
import com.cardealership.inventory.presentationlayer.inventory.InventoryResponseModel;
import com.cardealership.inventory.presentationlayer.inventory.InventoryVehicleResponseModel;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseModel> getInventories();
    InventoryVehicleResponseModel getInventoryByInventoryId(String inventoryId);
    InventoryResponseModel updateInventory(String inventoryId, InventoryRequestModel inventoryRequestModel);
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel);
    void deleteInventory(String inventoryId);
}
