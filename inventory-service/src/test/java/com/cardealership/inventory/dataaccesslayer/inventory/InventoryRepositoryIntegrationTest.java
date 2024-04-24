package com.cardealership.inventory.dataaccesslayer.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InventoryRepositoryIntegrationTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setupDb(){
        inventoryRepository.deleteAll();
    }

    @Test
    public void whenInventoryExists_ReturnInventoryByInventoryId() {
        //arrange
        InventoryType inventoryType = InventoryType.VEHICLE;

        Inventory inventory = new Inventory(inventoryType, new InventoryIdentifier());
        inventoryRepository.save(inventory);

        //act
        Inventory savedInventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventory.getInventoryIdentifier().getInventoryId());

        //assert
        assertNotNull(savedInventory);
        assertEquals(inventory.getInventoryIdentifier(), savedInventory.getInventoryIdentifier());
        assertEquals(inventory.getType(), savedInventory.getType());

    }

    @Test
    public void whenInventoryDoesNotExist_ReturnNull() {
        //act
        Inventory savedInventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId("1234");

        //assert
        assertNull(savedInventory);
    }
}