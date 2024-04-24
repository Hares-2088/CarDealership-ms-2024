package com.cardealership.employees.businesslayer.department;


import com.cardealership.employees.presentationlayer.department.DepartmentRequestDTO;
import com.cardealership.employees.presentationlayer.department.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDTO> getDepartments();
    DepartmentResponseDTO getDepartmentByDepartmentId(String departmentId);
    DepartmentResponseDTO updateDepartment(String departmentId, DepartmentRequestDTO departmentRequestDTO);
    DepartmentResponseDTO addDepartment(DepartmentRequestDTO departmentRequestDTO);
    void deleteDepartment(String departmentId);

}
