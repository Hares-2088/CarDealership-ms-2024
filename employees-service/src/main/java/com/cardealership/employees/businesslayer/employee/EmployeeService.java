package com.cardealership.employees.businesslayer.employee;



import com.cardealership.employees.presentationlayer.employee.EmployeeRequestDTO;
import com.cardealership.employees.presentationlayer.employee.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getEmployees();
    EmployeeResponseDTO getEmployeeByEmployeeId(String employeeIdentifier);
    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, String employeeIdentifier);
    EmployeeResponseDTO AddEmployee(EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployee(String employeeIdentifier);
}
