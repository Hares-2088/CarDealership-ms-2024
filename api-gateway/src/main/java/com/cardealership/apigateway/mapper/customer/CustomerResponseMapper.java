package com.cardealership.apigateway.mapper.customer;

import com.cardealership.apigateway.presentationlayer.customers.CustomerController;
import com.cardealership.apigateway.presentationlayer.customers.CustomerResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerResponseMapper {

    CustomerResponseModel responseModelToResponseModel(CustomerResponseModel responseModel);

    List<CustomerResponseModel> responseModelListToResponseModelList(List<CustomerResponseModel> responseModel);

    @AfterMapping
    default void afterMapping(@MappingTarget CustomerResponseModel customerResponseModel) {

        //customer Link
        Link selfLink = linkTo(methodOn(CustomerController.class)
                .getCustomerByCustomerId(customerResponseModel.getCustomerId()))
                .withSelfRel();

        customerResponseModel.add(selfLink);

        //all customers link
        Link allLink = linkTo(methodOn(CustomerController.class)
                .getAllCustomers())
                .withRel("all Customers");

        customerResponseModel.add(allLink);
    }
}
