package com.gonnect.debezium.kafka.bank.account.service;


import com.gonnect.debezium.kafka.bank.account.dto.Order;
import com.gonnect.debezium.kafka.bank.account.repository.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public Producer<String, String> producer;
    Gson gson = new Gson();

    @Transactional
    public void createOrder(Long orderNumber, Long customerId, Long productId) {

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setOrderNumber(orderNumber);
        producer.send(new ProducerRecord("order_check", orderNumber.toString(), createWrapper(order)));
    }

    private String createWrapper(Order order) {
        JsonObject cmd = new JsonObject();
        cmd.addProperty("action", "create");
        cmd.add("object", gson.toJsonTree(order));
        return cmd.toString();
    }
}
