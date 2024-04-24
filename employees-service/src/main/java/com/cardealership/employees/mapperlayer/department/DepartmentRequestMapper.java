package com.cardealership.employees.mapperlayer.department;


import com.cardealership.employees.dataaccesslayer.department.Department;
import com.cardealership.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.cardealership.employees.presentationlayer.department.DepartmentRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DepartmentRequestMapper {

    @Mapping(target = "id", ignore = true)
    Department requestDTOToEntity(DepartmentRequestDTO departmentRequestDTO, DepartmentIdentifier departmentIdentifier);
}
