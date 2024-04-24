package com.cardealership.employees.mapperlayer.employee;


import com.cardealership.employees.dataaccesslayer.employee.Employee;
import com.cardealership.employees.dataaccesslayer.employee.EmployeeIdentifier;
import com.cardealership.employees.presentationlayer.employee.EmployeeRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    @Mapping(target = "id", ignore = true)
    Employee requestModelToEntity(EmployeeRequestDTO employeeRequestDTO, EmployeeIdentifier employeeIdentifier);
}
