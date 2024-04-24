package com.cardealership.inventory.presentationlayer.inventory;

import com.cardealership.inventory.businesslayer.inventory.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {
    
    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public ResponseEntity<List<InventoryResponseModel>> getInventories(){
        return ResponseEntity.status(HttpStatus.FOUND).body(inventoryService.getInventories());
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryVehicleResponseModel> getInventoryByInventoryId(@PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.FOUND).body(inventoryService.getInventoryByInventoryId(inventoryId));
    }

    @PostMapping()
    public ResponseEntity<InventoryResponseModel> createInventory(@RequestBody InventoryRequestModel contactRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addInventory(contactRequestModel));
    }
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseModel> updateInventory(@RequestBody InventoryRequestModel inventoryRequestModel,
                                                              @PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateInventory(inventoryId, inventoryRequestModel));
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseModel> deleteInventory(@PathVariable String inventoryId){
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
