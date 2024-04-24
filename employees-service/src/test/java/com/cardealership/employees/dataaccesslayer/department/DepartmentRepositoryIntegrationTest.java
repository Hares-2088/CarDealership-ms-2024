package com.cardealership.employees.dataaccesslayer.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryIntegrationTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setupDb(){
        departmentRepository.deleteAll();
    }

    @Test
    public void whenDepartmentExists_ReturnDepartmentByDepartmentId() {
        //arrange
        Department department = new Department("HR", 5, new ArrayList<>());
        departmentRepository.save(department);

        //act
        Department savedDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId(department.getDepartmentIdentifier().getDepartmentId());

        //assert
        assertNotNull(savedDepartment);
        assertEquals(department.getDepartmentIdentifier(), savedDepartment.getDepartmentIdentifier());
        assertEquals(department.getName(), savedDepartment.getName());
        assertEquals(department.getHeadCount(), savedDepartment.getHeadCount());
    }

    @Test
    public void whenDepartmentDoesNotExist_ReturnNull() {
        //act
        Department savedDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId("1234");

        //assert
        assertNull(savedDepartment);
    }

}