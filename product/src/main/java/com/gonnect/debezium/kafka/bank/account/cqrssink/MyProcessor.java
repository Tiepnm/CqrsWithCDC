package com.gonnect.debezium.kafka.bank.account.cqrssink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {
    String ORDER_CHECK="orderCheck";
    String OUTPUT = "output2";

    @Input
    SubscribableChannel orderCheck();


    @Output("output2")
    MessageChannel output2();
}
