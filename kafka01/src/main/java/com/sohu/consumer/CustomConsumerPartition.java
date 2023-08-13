package com.sohu.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 消费某一主题下的特定分区数据
 *
 * /opt/module/kafka/bin/kafka-topics.sh --bootstrap-server hadoop102:9092 --topic first --describe
 */
public class CustomConsumerPartition {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 创建一个消费者
        KafkaConsumer<String, String> kafkaConsumer= new KafkaConsumer<>(properties);

        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("first", 2));
        // 订阅主题对应的特定分区
        kafkaConsumer.assign(topicPartitions);

        // 拉取数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords =kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                System.out.println(consumerRecord);
            }
        }
    }
}
