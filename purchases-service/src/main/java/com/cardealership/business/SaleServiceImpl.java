package com.cardealership.business;


import com.cardealership.dataaccess.Price;
import com.cardealership.dataaccess.Sale;
import com.cardealership.dataaccess.SaleIdentifier;
import com.cardealership.dataaccess.SaleRepository;
import com.cardealership.domainclientlayer.customer.CustomerModel;
import com.cardealership.domainclientlayer.customer.CustomerServiceClient;
import com.cardealership.domainclientlayer.employee.EmployeeModel;
import com.cardealership.domainclientlayer.employee.EmployeeServiceClient;
import com.cardealership.domainclientlayer.inventories.Status;
import com.cardealership.domainclientlayer.inventories.VehicleModel;
import com.cardealership.domainclientlayer.inventories.VehicleServiceClient;
import com.cardealership.mapper.SaleRequestMapper;
import com.cardealership.mapper.SaleResponseMapper;
import com.cardealership.presentation.SaleRequestModel;
import com.cardealership.presentation.SaleResponseModel;
import com.cardealership.utils.exceptions.InvalidInputException;
import com.cardealership.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SaleServiceImpl implements  SaleService{

    // reference attributes
    private final SaleRepository saleRepository;
    private final SaleResponseMapper saleResponseMapper;
    private final SaleRequestMapper saleRequestMapper;

    private final EmployeeServiceClient employeeServiceClient;
    private final VehicleServiceClient vehicleServiceClient;
    private final CustomerServiceClient customerServiceClient;


    public SaleServiceImpl(SaleRepository saleRepository, SaleResponseMapper saleResponseMapper, SaleRequestMapper saleRequestMapper, EmployeeServiceClient employeeServiceClient, VehicleServiceClient vehicleServiceClient, CustomerServiceClient customerServiceClient) {
        this.employeeServiceClient = employeeServiceClient;
        this.vehicleServiceClient = vehicleServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.saleRepository = saleRepository;
        this.saleResponseMapper = saleResponseMapper;
        this.saleRequestMapper = saleRequestMapper;
    }


    @Override
    public List<SaleResponseModel> getAllPurchases(String customerId) {
        CustomerModel customer = customerServiceClient.getCustomerByCustomerId(customerId);

        if (customer == null) {
            throw new InvalidInputException("Customer not found");
        }

        List<Sale> customerPurchases = saleRepository.findSalesByCustomerModel_CustomerId(customerId);

        return saleResponseMapper.entitiesToListResponseModel(customerPurchases);
    }

    @Override
    public SaleResponseModel getCustomerPurchaseBySaleId(String customerId, String saleId) {

        //verify customer exists
        CustomerModel customer = customerServiceClient.getCustomerByCustomerId(customerId);

        if (customer == null) {
            throw new InvalidInputException("Customer not found");
        }

        // verify sale exists
        Sale sale = saleRepository.findSaleByCustomerModel_CustomerIdAndSaleIdentifier_SaleId(customerId, saleId);
        if (sale == null) {
            throw new NotFoundException("Unknown SaleId provided" + saleId);
        }

        return saleResponseMapper.entityToResponseModel(sale);
    }

    @Override
    public SaleResponseModel addCustomerPurchase(String customerId, SaleRequestModel saleRequestModel) {
        //verify customer exists
        CustomerModel customer = customerServiceClient.getCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new InvalidInputException("Customer not found");
        }
        //verify employee exists
        EmployeeModel employee = employeeServiceClient.getEmployeeByEmployeeId(saleRequestModel.getEmployeeId());
        if (employee == null) {
            throw new InvalidInputException("Employee not found");
        }

        //verify that vehicle is in inventory
        VehicleModel vehicle = vehicleServiceClient.getVehicleByInventoryIdAndVehicleId(saleRequestModel.getInventoryId(), saleRequestModel.getVehicleId());
        if (vehicle == null) {
            throw new InvalidInputException("Vehicle not found in the inventory" + saleRequestModel.getInventoryId());
        }

        //verify that vehicle is not sold
        if (vehicle.getStatus() != Status.Available) {
            throw new InvalidInputException("Vehicle already sold");
        }

        //convert request model to entity
        Sale sale = saleRequestMapper.requestModelToEntity(saleRequestModel, new SaleIdentifier(), vehicle, employee, customer,
                new Price(saleRequestModel.getSalePrice(), saleRequestModel.getCurrency()));

        //using patch to update the vehicle status
        vehicleServiceClient.patchVehicle(vehicle.getVehicleId(), vehicle.getInventoryId(),Status.Sold.toString());

        //save sale
        Sale savedSale = saleRepository.save(sale);
        return saleResponseMapper.entityToResponseModel(savedSale);
    }

    @Override
    public SaleResponseModel updateSale(SaleRequestModel saleRequestModel, String saleId, String customerId) {

        Sale sale = saleRepository.findSaleByCustomerModel_CustomerIdAndSaleIdentifier_SaleId(customerId, saleId);

        if (sale == null) {
            throw new NotFoundException("Sale not found");
        }

        //verify customer exists
        CustomerModel customer = customerServiceClient.getCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new InvalidInputException("Customer not found");
        }

        //verify employee exists
        EmployeeModel employee = employeeServiceClient.getEmployeeByEmployeeId(saleRequestModel.getEmployeeId());
        if (employee == null) {
            throw new InvalidInputException("Employee not found");
        }
        //verify that vehicle is in inventory
        VehicleModel vehicle = vehicleServiceClient.getVehicleByInventoryIdAndVehicleId(saleRequestModel.getInventoryId(), saleRequestModel.getVehicleId());
        if (vehicle == null) {
            throw new InvalidInputException("Vehicle not found in the inventory" + saleRequestModel.getInventoryId());
        }
        //verify that vehicle is not sold
        if (vehicle.getStatus() != Status.Available) {
            throw new InvalidInputException("Vehicle already sold");
        }

        Sale updatedSale = saleRequestMapper.requestModelToEntity(saleRequestModel, sale.getSaleIdentifier(), vehicle, employee, customer,
                new Price(saleRequestModel.getSalePrice(), saleRequestModel.getCurrency()));

        return saleResponseMapper.entityToResponseModel(saleRepository.save(updatedSale));
    }

    @Override
    public void deleteSale(String saleId, String customerId) {
        Sale sale = saleRepository.findSaleByCustomerModel_CustomerIdAndSaleIdentifier_SaleId(customerId, saleId);
        if (sale == null) {
            throw new NotFoundException("Sale not found");
        }

        //verify customer exists
        CustomerModel customer = customerServiceClient.getCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new InvalidInputException("Customer not found");
        }

        //if they exist, delete the sale
        saleRepository.delete(sale);
    }

}