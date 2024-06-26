package com.cardealership.customers.mapper;


import com.cardealership.customers.datalayer.Address;
import com.cardealership.customers.datalayer.Customer;
import com.cardealership.customers.datalayer.CustomerIdentifier;
import com.cardealership.customers.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
    })
    Customer requestModelToEntity(CustomerRequestModel customerRequestModel, CustomerIdentifier customerIdentifier,
                                  Address address);
}
