package com.azizsaparniyazov.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bucket {
    private UUID id;
    private String userId;
    private String customerId;
    private List <Good> goods;
    private BigDecimal totalPrice;

}
