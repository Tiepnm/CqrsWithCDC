package com.gonnect.debezium.kafka.bank.account.cqrssink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {
    String INPUT = "input2";
    String OUTPUT = "output2";
    String ORDER_CHECK="orderCheck";

    @Input
    SubscribableChannel input2();

    @Input
    SubscribableChannel orderCheck();

    @Output("output2")
    MessageChannel output2();



}
