package com.cardealership.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Warranty {

    private LocalDate warrantyEndDate;
    private String warrantyTerms;
}
