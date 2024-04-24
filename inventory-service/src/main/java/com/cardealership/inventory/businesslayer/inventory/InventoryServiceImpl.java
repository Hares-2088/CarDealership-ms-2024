package com.cardealership.inventory.businesslayer.inventory;


import com.cardealership.inventory.dataaccesslayer.inventory.Inventory;
import com.cardealership.inventory.dataaccesslayer.inventory.InventoryIdentifier;
import com.cardealership.inventory.dataaccesslayer.inventory.InventoryRepository;
import com.cardealership.inventory.dataaccesslayer.vehicle.Vehicle;
import com.cardealership.inventory.dataaccesslayer.vehicle.VehicleRepository;
import com.cardealership.inventory.mapperlayer.inventory.InventoryRequestMapper;
import com.cardealership.inventory.mapperlayer.inventory.InventoryResponseMapper;
import com.cardealership.inventory.mapperlayer.inventory.InventoryVehicleResponseMapper;
import com.cardealership.inventory.mapperlayer.vehicle.VehicleResponseMapper;
import com.cardealership.inventory.presentationlayer.inventory.InventoryRequestModel;
import com.cardealership.inventory.presentationlayer.inventory.InventoryResponseModel;
import com.cardealership.inventory.presentationlayer.inventory.InventoryVehicleResponseModel;
import com.cardealership.inventory.presentationlayer.vehicle.VehicleResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private InventoryRepository inventoryRepository;
    private VehicleRepository vehicleRepository;
    private InventoryRequestMapper inventoryRequestMapper;
    private InventoryResponseMapper inventoryResponseMapper;
    private InventoryVehicleResponseMapper inventoryVehicleResponseMapper;
    private final VehicleResponseMapper vehicleResponseMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, VehicleRepository vehicleRepository, InventoryRequestMapper inventoryRequestMapper, InventoryResponseMapper inventoryResponseMapper, InventoryVehicleResponseMapper inventoryVehicleResponseMapper, VehicleResponseMapper vehicleResponseMapper) {
        this.inventoryRepository = inventoryRepository;
        this.vehicleRepository = vehicleRepository;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.inventoryVehicleResponseMapper = inventoryVehicleResponseMapper;
        this.vehicleResponseMapper = vehicleResponseMapper;
    }

    @Override
    public List<InventoryResponseModel> getInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();

        return inventoryResponseMapper.entityListToResponseModelList(inventoryList);
    }

    @Override
    public InventoryVehicleResponseModel getInventoryByInventoryId(String inventoryId) {
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);
        List<Vehicle> vehicleList = vehicleRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);
        List<VehicleResponseModel> vehicleResponseModelList = vehicleResponseMapper.entityListToResponseModelList(vehicleList);

        return inventoryVehicleResponseMapper.entityToResponseModel(inventory, vehicleResponseModelList);
    }

    @Override
    public InventoryResponseModel updateInventory(String inventoryId, InventoryRequestModel inventoryRequestModel) {
        Inventory foundInventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, foundInventory.getInventoryIdentifier());
        inventory.setId(foundInventory.getId());
        return inventoryResponseMapper.entityToResponseModel(inventory);
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, new InventoryIdentifier());

        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(String inventoryId) {
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);

        inventoryRepository.delete(inventory);
    }
}
