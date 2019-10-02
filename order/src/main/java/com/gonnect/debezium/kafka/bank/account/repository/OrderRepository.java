package com.gonnect.debezium.kafka.bank.account.repository;

import com.gonnect.debezium.kafka.bank.account.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
