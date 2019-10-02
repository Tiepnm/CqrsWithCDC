package com.gonnect.debezium.kafka.bank.account.cqrssink;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CqrdReadModelUpdater {


//    @Autowired
//    private MyProcessor processor;

//    @StreamListener(MyProcessor.INPUT)
//    public void processOrderCheck(String message) {
//        System.out.println("check result");
//        System.out.println(message);
//
//    }

}
