package com.cardealership.inventory.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class VehicleIdentifier {

    private String vehicleId;

    public VehicleIdentifier() {
        this.vehicleId = UUID.randomUUID().toString();
    }

    public VehicleIdentifier(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
