package com.cardealership.inventory.presentationlayer.vehicle;

import com.cardealership.inventory.dataaccesslayer.vehicle.Manufacturer;
import com.cardealership.inventory.dataaccesslayer.vehicle.Options;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VehicleRequestModel {
    private List<Manufacturer> manufacturers;
    private List<Options> options;
    private String make;
    private String model;
    private Integer year;
    private String status;
    private String usageType;
    private String type;
    private String inventoryId;
}
