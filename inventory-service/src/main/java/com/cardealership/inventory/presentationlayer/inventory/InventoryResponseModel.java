package com.cardealership.inventory.presentationlayer.inventory;

import com.cardealership.inventory.dataaccesslayer.inventory.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseModel {

    private String inventoryId;

    private InventoryType type;

}
