package com.cardealership.domainclientlayer.inventories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
public class VehicleModel {

    String vehicleId;
    String inventoryId;
    String make;
    String model;
    Status status;

}
