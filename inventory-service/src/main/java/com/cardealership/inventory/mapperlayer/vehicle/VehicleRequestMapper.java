package com.cardealership.inventory.mapperlayer.vehicle;


import com.cardealership.inventory.dataaccesslayer.vehicle.Vehicle;
import com.cardealership.inventory.dataaccesslayer.vehicle.VehicleIdentifier;
import com.cardealership.inventory.presentationlayer.vehicle.VehicleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(expression = "java(vehicle.getOptions())", target = "options")
    Vehicle requestModelToEntity(VehicleRequestModel vehicleRequestModel, VehicleIdentifier vehicleIdentifier);
}
