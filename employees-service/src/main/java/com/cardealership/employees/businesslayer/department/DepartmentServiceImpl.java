package com.cardealership.employees.businesslayer.department;


import com.cardealership.employees.dataaccesslayer.department.Department;
import com.cardealership.employees.dataaccesslayer.department.DepartmentIdentifier;
import com.cardealership.employees.dataaccesslayer.department.DepartmentRepository;
import com.cardealership.employees.mapperlayer.department.DepartmentRequestMapper;
import com.cardealership.employees.mapperlayer.department.DepartmentResponseMapper;
import com.cardealership.employees.presentationlayer.department.DepartmentRequestDTO;
import com.cardealership.employees.presentationlayer.department.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final DepartmentResponseMapper departmentResponseMapper;
    private final DepartmentRequestMapper departmentRequestMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentResponseMapper departmentResponseMapper, DepartmentRequestMapper departmentRequestMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentResponseMapper = departmentResponseMapper;
        this.departmentRequestMapper = departmentRequestMapper;
    }

    @Override
    public List<DepartmentResponseDTO> getDepartments() {

        List<Department> departmentList = departmentRepository.findAll();

        return departmentResponseMapper.entityListToResponseDTOList(departmentList);
    }

    @Override
    public DepartmentResponseDTO getDepartmentByDepartmentId(String departmentId) {

        Department foundDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId(departmentId);

        return departmentResponseMapper.entityToResponseDTO(foundDepartment);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(String departmentId, DepartmentRequestDTO departmentRequestDTO) {

        Department foundDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId(departmentId);

        Department department = departmentRequestMapper.requestDTOToEntity(departmentRequestDTO, foundDepartment.getDepartmentIdentifier());
        department.setId(foundDepartment.getId());


        return departmentResponseMapper.entityToResponseDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentResponseDTO addDepartment(DepartmentRequestDTO departmentRequestDTO) {

        Department department = departmentRequestMapper.requestDTOToEntity(departmentRequestDTO, new DepartmentIdentifier());

        return departmentResponseMapper.entityToResponseDTO(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(String departmentId) {
        Department foundDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId(departmentId);

        departmentRepository.delete(foundDepartment);
    }
}
