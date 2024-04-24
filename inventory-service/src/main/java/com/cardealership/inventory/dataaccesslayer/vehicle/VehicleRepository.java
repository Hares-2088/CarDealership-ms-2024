package com.cardealership.inventory.dataaccesslayer.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findVehicleByVehicleIdentifier_VehicleId(String vehicleId);
    Vehicle findByInventoryIdentifier_InventoryIdAndVehicleIdentifier_VehicleId(String inventoryId, String vehicleId);
    List<Vehicle> findAllByInventoryIdentifier_InventoryId(String inventoryId);

    List<Vehicle> findAllByInventoryIdentifier_InventoryIdAndStatusEqualsAndUsageTypeEquals(String inventory, Status status, UsageType usageType);
    List<Vehicle> findAllByInventoryIdentifier_InventoryIdAndStatusEquals(String inventoryId, Status status);
    List<Vehicle> findAllByInventoryIdentifier_InventoryIdAndUsageTypeEquals(String inventoryId, UsageType usageType);
}
