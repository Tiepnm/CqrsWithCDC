package com.gonnect.debezium.kafka.bank.account.cqrssink;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
//@EnableBinding({MyProcessor.class})
public class CqrsReadModel {

    @Bean
    public KafkaStreams kafkaStreams() {
        final Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test1");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10 * 1024);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "C://tmp");
        props.put(CommonClientConfigs.METADATA_MAX_AGE_CONFIG, 500);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());


        final KafkaStreams kafkaStreams = new KafkaStreams(kafkaStreamTopology(), props);
        kafkaStreams.start();

        return kafkaStreams;
    }

    @Bean
    public Topology kafkaStreamTopology() {
        final StreamsBuilder streamsBuilder = new StreamsBuilder();
        final Serde<String> stringSerde = Serdes.String();
        StreamsBuilder builder = new StreamsBuilder();
        // streamsBuilder.stream("some_topic") etc ...
        KStream<String, String> productOrderResult =
                streamsBuilder.stream("product-output2", Consumed.with(stringSerde, stringSerde)).map((s, s2) -> {
                    OrderResult orderResult = null;
                    try {
                        orderResult = new ObjectMapper().readValue(s2, OrderResult.class);
                        return new KeyValue<>(orderResult.getOrderId().toString(), s2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
        KStream<String, String> customerOrderResult =
                streamsBuilder.stream("customer-output2", Consumed.with(stringSerde, stringSerde)).map((s, s2) -> {

                    try {

                        OrderResult orderResult = new ObjectMapper().readValue(s2, OrderResult.class);
                        return new KeyValue<>(orderResult.getOrderId().toString(), s2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;

                });
        productOrderResult.join(customerOrderResult, (orderResult, orderResult2) -> {
            try {
                OrderResult s1 = new ObjectMapper().readValue(orderResult, OrderResult.class);
                OrderResult s2 = new ObjectMapper().readValue(orderResult2, OrderResult.class);
                if(s1.getResult().equals("OK") && s2.getResult().equals("OK")){
                    System.out.println(orderResult2);
                }
                return orderResult2;
            } catch (IOException e) {
                e.printStackTrace();
            }
             return null;
        }, JoinWindows.of(TimeUnit.SECONDS.toMillis(20)));
        return streamsBuilder.build();
    }
}
