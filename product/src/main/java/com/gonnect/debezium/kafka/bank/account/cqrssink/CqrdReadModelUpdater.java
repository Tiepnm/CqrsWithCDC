package com.gonnect.debezium.kafka.bank.account.cqrssink;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class CqrdReadModelUpdater {


    @Autowired
    private MyProcessor processor;

    @StreamListener(MyProcessor.ORDER_CHECK)
    @SendTo(MyProcessor.OUTPUT)
    public OrderResult processOrderCheck(String message) {

        try{
            OrderCheck orderCheck = new ObjectMapper().readValue(message, OrderCheck.class);
            OrderResult orderResult = new OrderResult();
            orderResult.setResult("OK");
            orderResult.setOrderId(orderCheck.getObject().getOrderNumber());
            orderResult.setFromApp("product");
            return orderResult;
        }
        catch (Exception e) {
            return null;
        }

    }

}
