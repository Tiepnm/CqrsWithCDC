package com.gonnect.debezium.kafka.bank.account.cqrssink;


import com.gonnect.debezium.kafka.bank.account.dto.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderCheck {
    private String action;
    private Order object;

}


