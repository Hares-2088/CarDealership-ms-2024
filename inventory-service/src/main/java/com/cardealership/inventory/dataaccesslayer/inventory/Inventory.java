package com.cardealership.inventory.dataaccesslayer.inventory;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventories")
@NoArgsConstructor
@Setter
@Getter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    @Enumerated(EnumType.STRING)
    private InventoryType type;


    public Inventory(InventoryType type,InventoryIdentifier inventoryIdentifier) {
        this.type = type;
        this.inventoryIdentifier = inventoryIdentifier;
    }
}
