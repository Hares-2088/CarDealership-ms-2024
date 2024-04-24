package com.cardealership.domainclientlayer.inventories;

import com.cardealership.utils.exceptions.HttpErrorInfo;
import com.cardealership.utils.exceptions.InvalidInputException;
import com.cardealership.utils.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
@Slf4j
public class VehicleServiceClient {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final String VEHICLE_SERVICE_BASE_URL;

    private VehicleServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                 @Value("${app.inventory-service.host}") String vehicleServiceHost,
                                 @Value("${app.inventory-service.port}") String vehicleServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        VEHICLE_SERVICE_BASE_URL = "http://" + vehicleServiceHost + ":" + vehicleServicePort + "/api/v1/vehicles";
    }

    public VehicleModel getVehicleByInventoryIdAndVehicleId(String inventoryId, String vehicleId) {
        try{
            String url = VEHICLE_SERVICE_BASE_URL + "/" + inventoryId + "/" + vehicleId;

            VehicleModel vehicleModel = restTemplate.getForObject(url, VehicleModel.class);

            return vehicleModel;
        } catch (HttpClientErrorException ex) {
            throw  handleHttpClientException(ex);
        }
    }

    //add a patching method receiving only the vehicle id and the vehicle new status
    public VehicleModel patchVehicle(String vehicleId, String inventoryId, String status) {
        try {
            String url = VEHICLE_SERVICE_BASE_URL + "/" + inventoryId + "/" + vehicleId + "/status" + status;
            VehicleModel vehicleModel = restTemplate.patchForObject(url, status, VehicleModel.class);
            return vehicleModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        //include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }
}
