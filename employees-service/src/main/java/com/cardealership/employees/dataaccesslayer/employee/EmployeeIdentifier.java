package com.cardealership.employees.dataaccesslayer.employee;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class EmployeeIdentifier {
    private String employeeId;
    public EmployeeIdentifier(){
        this.employeeId = UUID.randomUUID().toString();
    }

    public EmployeeIdentifier(String employeeId) {
        this.employeeId = employeeId;
    }
}