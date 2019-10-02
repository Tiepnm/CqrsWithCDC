package com.gonnect.debezium.kafka.bank.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Getter
    @Column(name = "order_number")
    private Long orderNumber;
    @Getter
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Getter
    @Column(name = "quantity")
    private Integer quantity;
    @Getter
    @Column(name = "product_id")
    private Long productId;

    @Getter
    @Column(name = "purchaser")
    private Long purchaser;

}
