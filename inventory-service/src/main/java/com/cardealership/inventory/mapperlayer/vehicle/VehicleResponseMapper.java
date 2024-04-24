package com.cardealership.inventory.mapperlayer.vehicle;


import com.cardealership.inventory.dataaccesslayer.vehicle.Vehicle;
import com.cardealership.inventory.presentationlayer.vehicle.VehicleResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleResponseMapper {

    @Mapping(expression = "java(vehicle.getVehicleIdentifier().getVehicleId())", target = "vehicleId")
    @Mapping(expression = "java(vehicle.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(vehicle.getPrice().getCurrency().toString())", target = "currency")
    @Mapping(expression = "java(vehicle.getPrice().getPrice())", target = "price")
    VehicleResponseModel entityToResponseModel(Vehicle vehicle);

    List<VehicleResponseModel> entityListToResponseModelList(List<Vehicle> vehicles);

//    @AfterMapping
//    default  void addLinks(@MappingTarget VehicleResponseModel model, Vehicle vehicle){
//        //self Link
//        Link selfLink= linkTo(methodOn(VehicleController.class)
//                .getVehicleById(model.getInventoryId(), model.getVehicleId()))
//                .withSelfRel();
//        model.add(selfLink);
//    }
}
