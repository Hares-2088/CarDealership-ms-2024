package com.cardealership.inventory.dataaccesslayer.vehicle;

import com.cardealership.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@Setter
@Getter
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private VehicleIdentifier vehicleIdentifier;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="vehicles_manufacturer", joinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Manufacturer> manufacturers;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="vehicles_options", joinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Options> options;

    private String make;

    private String model;

    @Column(name = "\"year\"")
    private Integer year;

    private Price price;

    public Vehicle(InventoryIdentifier inventoryIdentifier, @NotNull List<Manufacturer> manufacturers, @NotNull  List<Options> options, @NotNull  String make, @NotNull  String model, @NotNull  Integer year, @NotNull  Status status, @NotNull  UsageType type) {
        if (inventoryIdentifier == null) {
            throw new IllegalArgumentException("InventoryIdentifier must not be null");
        }

        this.inventoryIdentifier = inventoryIdentifier;
        this.vehicleIdentifier = new VehicleIdentifier();
        this.manufacturers = manufacturers;
        this.options = options;
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status;
        this.usageType = type;
    }
}
