package com.gonnect.debezium.kafka.bank.account.api;

import com.gonnect.debezium.kafka.bank.account.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order")
public class OrderApi {


    @Autowired
    private OrderService orderService;

    @PostMapping
    ResponseEntity createOrder(@RequestBody OrderCreateCommand order) {
        orderService.createOrder(order.getOrderNumber(), order.getCustomerId(), order.getProductId());
        return ResponseEntity.ok().build();
    }
}
