package com.cardealership.inventory.dataaccesslayer.vehicle;

import com.cardealership.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VehicleRepositoryIntegrationTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setupDb(){
        vehicleRepository.deleteAll();
    }

    @Test
    public void whenVehicleExists_ReturnVehicleByVehicleId() {
        //arrange
        Status status = Status.Available;
        UsageType usageType = UsageType.NEW;

        List<Manufacturer> manufacturers = new ArrayList<>();
        List<Options> options = new ArrayList<>();

        Vehicle vehicle = new Vehicle(new InventoryIdentifier(), manufacturers, options, "Camry", "make", 2021, status, usageType);
        vehicleRepository.save(vehicle);

        //act
        Vehicle savedVehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(vehicle.getVehicleIdentifier().getVehicleId());

        //assert
        assertNotNull(savedVehicle);
        assertEquals(vehicle.getVehicleIdentifier(), savedVehicle.getVehicleIdentifier());
        assertEquals(vehicle.getMake(), savedVehicle.getMake());
        assertEquals(vehicle.getModel(), savedVehicle.getModel());
        assertEquals(vehicle.getYear(), savedVehicle.getYear());
        assertEquals(vehicle.getStatus(), savedVehicle.getStatus());
        assertEquals(vehicle.getUsageType(), savedVehicle.getUsageType());

    }

    @Test
    public void whenVehicleDoesNotExist_ReturnNull() {
        //act
        Vehicle savedVehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId("1234");

        //assert
        assertNull(savedVehicle);
    }
}