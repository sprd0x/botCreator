package com.azizsaparniyazov.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
    private String id;
    private String name;
    private String botName;
    private String botToken;
    private BigDecimal balance;
    private LocalDate startDate;
}
