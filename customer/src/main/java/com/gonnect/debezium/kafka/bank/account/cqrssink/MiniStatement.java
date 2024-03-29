package com.gonnect.debezium.kafka.bank.account.cqrssink;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class MiniStatement {

    private String id;
    private BigDecimal amount;
    private String debitCardId;
    private Date transactionDate;
    private String time = "2019-09-06T00:46:58.771Z";
}
