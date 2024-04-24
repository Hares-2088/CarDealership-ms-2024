package com.cardealership.dataaccess;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SaleIdentifier {

    private String saleId;

    public SaleIdentifier() {
        this.saleId = UUID.randomUUID().toString();
    }
}
