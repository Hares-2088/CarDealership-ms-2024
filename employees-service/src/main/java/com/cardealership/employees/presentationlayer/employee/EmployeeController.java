package com.cardealership.employees.presentationlayer.employee;

import com.cardealership.employees.businesslayer.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByEmployeeId(@PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeeByEmployeeId(employeeId));
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO contactRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.AddEmployee(contactRequestDTO));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO,
                                                            @PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeRequestDTO, employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable String employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
