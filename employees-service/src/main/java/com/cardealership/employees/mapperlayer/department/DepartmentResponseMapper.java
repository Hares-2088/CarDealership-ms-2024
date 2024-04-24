package com.cardealership.employees.mapperlayer.department;



import com.cardealership.employees.dataaccesslayer.department.Department;
import com.cardealership.employees.presentationlayer.department.DepartmentController;
import com.cardealership.employees.presentationlayer.department.DepartmentResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DepartmentResponseMapper {

    @Mapping(expression = "java(department.getDepartmentIdentifier().getDepartmentId())", target = "departmentId")
    DepartmentResponseDTO entityToResponseDTO(Department department);

    List<DepartmentResponseDTO> entityListToResponseDTOList(List<Department> departmentList);

//    @AfterMapping
//    default void addLinks (@MappingTarget DepartmentResponseDTO departmentResponseDTO, Department department) {
//        Link selfLink = linkTo(methodOn(DepartmentController.class).
//                getDepartmentByDepartmentId(departmentResponseDTO.getDepartmentId())).withSelfRel();
//        departmentResponseDTO.add(selfLink);
//    }
}
