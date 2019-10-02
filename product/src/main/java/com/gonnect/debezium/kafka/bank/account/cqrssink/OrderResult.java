package com.gonnect.debezium.kafka.bank.account.cqrssink;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderResult {
    private Long orderId;
    private String result;
    private String fromApp;
}
