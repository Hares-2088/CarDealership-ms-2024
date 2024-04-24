package com.cardealership.inventory.presentationlayer.inventory;

import com.cardealership.inventory.presentationlayer.vehicle.VehicleResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryVehicleResponseModel {

    private String inventoryId;
    private String type;
    private List<VehicleResponseModel> availableVehicles;

}
