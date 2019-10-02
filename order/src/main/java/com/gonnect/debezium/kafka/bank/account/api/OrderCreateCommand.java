package com.gonnect.debezium.kafka.bank.account.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateCommand {
    private Long orderNumber;
    private Long customerId;
    private Long productId;
}
