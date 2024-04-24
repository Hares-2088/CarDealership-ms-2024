package com.cardealership.employees.presentationlayer.department;

import com.cardealership.employees.businesslayer.department.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentResponseDTO>> getDepartments(){
        return ResponseEntity.status(HttpStatus.FOUND).body(departmentService.getDepartments());
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByDepartmentId(@PathVariable String departmentId){
        return ResponseEntity.status(HttpStatus.FOUND).body(departmentService.getDepartmentByDepartmentId(departmentId));
    }

    @PostMapping()
    public ResponseEntity<DepartmentResponseDTO> addDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentRequestDTO));
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO,
                                                             @PathVariable String departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(departmentId, departmentRequestDTO));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> deleteDepartment(@PathVariable String departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
