package com.azizsaparniyazov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Transaction {

    private UUID id;
    private LocalDateTime dateTime;
    private String userId;
    private String customerId;

    public Transaction() {
        this.id = UUID.randomUUID();
    }
}
