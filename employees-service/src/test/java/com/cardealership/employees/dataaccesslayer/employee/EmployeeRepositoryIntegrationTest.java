package com.cardealership.employees.dataaccesslayer.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setupDb(){
        employeeRepository.deleteAll();
    }

    @Test
    public void whenEmployeeExists_ReturnEmployeeByEmployeeId() {
        //arrange
        Employee employee = new Employee(new Address("street", "city", "province", "country", "postal code"),
                new ArrayList<>(), "Adem", "Bessam", "adem@email", 1500.00, 15.5);

        //act
        Employee savedEmployee = employeeRepository.save(employee);

        //assert
        assertNotNull(savedEmployee);
        assertEquals(employee.getEmployeeIdentifier(), savedEmployee.getEmployeeIdentifier());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getAddress(), savedEmployee.getAddress());
        assertEquals(employee.getEmailAddress(), savedEmployee.getEmailAddress());
        assertEquals(employee.getSalary(), savedEmployee.getSalary());
        assertEquals(employee.getCommissionRate(), savedEmployee.getCommissionRate());
    }

    @Test
    public void whenEmployeeDoesNotExist_ReturnNull() {
        //act
        Employee savedEmployee = employeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId("1234");

        //assert
        assertNull(savedEmployee);
    }

}