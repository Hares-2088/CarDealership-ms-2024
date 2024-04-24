package com.cardealership.inventory.presentationlayer.inventory;


import com.cardealership.inventory.dataaccesslayer.inventory.InventoryType;
import com.cardealership.inventory.dataaccesslayer.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestModel {

    private InventoryType type;

    private List<Vehicle> vehicles;
}
