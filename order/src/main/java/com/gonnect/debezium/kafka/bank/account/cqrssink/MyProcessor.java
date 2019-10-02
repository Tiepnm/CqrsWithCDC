package com.gonnect.debezium.kafka.bank.account.cqrssink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {
    String INPUT = "output2";

    @Input
    SubscribableChannel output2();

}
