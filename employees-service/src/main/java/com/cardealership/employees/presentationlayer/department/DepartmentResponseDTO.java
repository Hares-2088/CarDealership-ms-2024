package com.cardealership.employees.presentationlayer.department;

import com.cardealership.employees.dataaccesslayer.department.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class DepartmentResponseDTO {

    private String departmentId;
    private String name;
    private Integer headCount;
    private List<Position> positions;
}

