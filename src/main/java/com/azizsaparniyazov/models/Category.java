package com.azizsaparniyazov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data

public class Category {
    private UUID id;
    private String name;
    private String userId;

    public Category() {
        this.id = UUID.randomUUID();
    }
}
