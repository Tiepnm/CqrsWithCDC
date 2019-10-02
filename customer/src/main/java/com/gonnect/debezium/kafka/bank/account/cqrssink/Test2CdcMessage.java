package com.gonnect.debezium.kafka.bank.account.cqrssink;

public class Test2CdcMessage {
    private Test2Operator payload;

    public Test2CdcMessage() {
    }

    public Test2CdcMessage(Test2Operator payload) {
        this.payload = payload;
    }

    Test2Operator getPayload() {
        return payload;
    }

    void setPayload(Test2Operator payload) {
        this.payload = payload;
    }

    boolean isUpdate() {
        return payload.getOp().equals("u");
    }
}
