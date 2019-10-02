package com.gonnect.debezium.kafka.bank.account.cqrssink;

public class Test2Operator {
    private String op;
    private String ts_ms;
    private Test2Cdc before;
    private Test2Cdc after;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTs_ms() {
        return ts_ms;
    }

    public void setTs_ms(String ts_ms) {
        this.ts_ms = ts_ms;
    }

    public Test2Cdc getBefore() {
        return before;
    }

    public void setBefore(Test2Cdc before) {
        this.before = before;
    }

    public Test2Cdc getAfter() {
        return after;
    }

    public void setAfter(Test2Cdc after) {
        this.after = after;
    }
}
