package com.sohu.consumergroup02;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 修改对消费者的分区分配策略：粘性
 */
public class CustomConsumerMember03 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 修改对消费者的分区分配策略：粘性
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, StickyAssignor.class.getName());
        // 如果设置同一个组名，就会形成一个消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup02");
        ArrayList<String> topics = new ArrayList<>();
        topics.add("first");
        KafkaConsumer<String, String> kafkaConsumer= new KafkaConsumer<>(properties);

        kafkaConsumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, String> consumerRecords =kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                System.out.println(consumerRecord);
            }
        }
    }
}
