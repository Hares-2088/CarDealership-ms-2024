package com.cardealership.employees.presentationlayer.employee;


import com.cardealership.employees.dataaccesslayer.employee.Address;
import com.cardealership.employees.dataaccesslayer.employee.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeRequestDTO {
    private Address address;
    private List<PhoneNumber> phoneNumbers;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;
}
