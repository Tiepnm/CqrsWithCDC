package com.gonnect.debezium.kafka.bank.account.cqrssink;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonnect.debezium.kafka.bank.account.model.MoneyWithdrawal;
import com.gonnect.debezium.kafka.bank.account.repository.MoneyWithdrawalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;
import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

@Service
@Slf4j
public class CqrdReadModelUpdater {

    @Autowired
    private MyProcessor processor;

    private final MoneyWithdrawalRepository moneyWithdrawalRepository;

    CqrdReadModelUpdater(MoneyWithdrawalRepository moneyWithdrawalRepository) {
        this.moneyWithdrawalRepository = moneyWithdrawalRepository;
    }

    @StreamListener(INPUT)
    @SendTo(OUTPUT)
    public MiniStatement process(DebitCardCdcMessage message) {
        MiniStatement miniStatement = new MiniStatement();
        if(message.isUpdate()) {
            MoneyWithdrawal moneyWithdrawal = saveWithdrawalFrom(message);
           miniStatement.setId(moneyWithdrawal.getId());
           miniStatement.setAmount(moneyWithdrawal.getAmount());
           miniStatement.setDebitCardId(moneyWithdrawal.getDebitCardId());
           miniStatement.setTransactionDate(new Date(System.currentTimeMillis()));

        }

        log.info("Producing mini statement: " + miniStatement.toString());

        return miniStatement;
    }

    @StreamListener(MyProcessor.INPUT)
    @SendTo(MyProcessor.OUTPUT)
    public MiniStatement process2(Test2CdcMessage message) {
        log.info("process45....");
        System.out.println(message.getPayload().getAfter().getName());
        MiniStatement miniStatement = new MiniStatement();
        miniStatement.setTime("123");
        miniStatement.setAmount(BigDecimal.ONE);
        miniStatement.setTransactionDate(new Date(System.currentTimeMillis()));
        return miniStatement;
    }

    @StreamListener(MyProcessor.ORDER_CHECK)
    @SendTo(MyProcessor.OUTPUT)
    public OrderResult processOrderCheck(String message) throws IOException {

        OrderCheck orderCheck = new ObjectMapper().readValue(message, OrderCheck.class);
        OrderResult orderResult = new OrderResult();
        orderResult.setResult("OK");
        orderResult.setOrderId(orderCheck.getObject().getOrderNumber());
        orderResult.setFromApp("customer");
        return orderResult;
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
    private MoneyWithdrawal saveWithdrawalFrom(DebitCardCdcMessage message) {
        String debitCardId = message.getPayload().getBefore().getId();
        BigDecimal withdrawalAmount
                = balanceAfter(message).subtract(balanceBefore(message));
        return moneyWithdrawalRepository.save(new MoneyWithdrawal(withdrawalAmount, debitCardId));
    }

    private BigDecimal balanceAfter(DebitCardCdcMessage message) {
        return message.getPayload().getAfter().getUsed_limit();
    }

    private BigDecimal balanceBefore(DebitCardCdcMessage message) {
        return message.getPayload().getBefore().getUsed_limit();
    }
}
