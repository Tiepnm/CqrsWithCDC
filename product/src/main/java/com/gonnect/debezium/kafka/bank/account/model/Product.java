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
@Table(name = "products")
public class Product {

    @Id
    @Getter
    @Column(name = "id")
    private Long id;
    @Getter
    @Column(name = "name")
    private String name;
    @Getter
    @Column(name = "description")
    private Integer description;


}
