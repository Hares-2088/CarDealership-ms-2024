package com.cardealership.inventory.businesslayer.vehicle;



import com.cardealership.inventory.presentationlayer.vehicle.VehicleRequestModel;
import com.cardealership.inventory.presentationlayer.vehicle.VehicleResponseModel;

import java.util.List;
import java.util.Map;

public interface VehicleService {
    List<VehicleResponseModel> getVehicles(String vehicleId, Map<String,String> queryParams);
    VehicleResponseModel getVehicleByVehicleId(String vehicleId);
    VehicleResponseModel updateVehicle(String vehicleId, VehicleRequestModel vehicleRequestModel);
    VehicleResponseModel addVehicle(VehicleRequestModel vehicleRequestModel);
    VehicleResponseModel patchVehicle(String vehicleId, String status);
    void deleteVehicle(String vehicleId);
}
