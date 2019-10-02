package com.gonnect.debezium.kafka.bank.account.dto;

import lombok.Data;

@Data
public class Order {
    private Integer quantity;
    private Long productId;
    private Long customerId;
    private Long orderNumber;
}
