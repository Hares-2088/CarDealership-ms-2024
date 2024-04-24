package com.cardealership.employees.mapperlayer.employee;


import com.cardealership.employees.dataaccesslayer.employee.Employee;
import com.cardealership.employees.presentationlayer.employee.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    EmployeeResponseDTO entityToResponseDTO(Employee employee);

    List<EmployeeResponseDTO> entityListToResponseDTOList(List<Employee> employeeList);

//    @AfterMapping
//    default void addLinks(EmployeeResponseDTO model, Employee employee){
//        //self link
//        model.add(linkTo(methodOn(EmployeeController.class).
//                getEmployeeByEmployeeId(model.getEmployeeId())).
//                withSelfRel());
//
//        //all employees link
//        model.add(linkTo(methodOn(EmployeeController.class).getEmployees())
//                .withRel("employees"));
//    }
}
