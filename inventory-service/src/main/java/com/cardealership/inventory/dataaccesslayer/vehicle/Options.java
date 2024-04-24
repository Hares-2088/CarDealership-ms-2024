package com.cardealership.inventory.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
public class Options {
    private String optionName;

    private String description;

    private Double optionPrice;

    public Options(String name, String description, Double price) {
        this.optionName = name;
        this.description = description;
        this.optionPrice = price;
    }
}
