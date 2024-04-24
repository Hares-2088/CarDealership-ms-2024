package com.cardealership.inventory.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Manufacturer {

    private String name;

    private String country;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
