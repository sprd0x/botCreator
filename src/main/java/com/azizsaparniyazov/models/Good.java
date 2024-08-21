package com.azizsaparniyazov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Good {
    private UUID id;
    private String name;
    private BigDecimal price;
    private int categoryId;
    private String userId;

    public Good() {
        this.id = UUID.randomUUID();
    }
}
