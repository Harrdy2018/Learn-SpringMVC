package com.sohu.consumergroup;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 默认对消费者的分区分配策略：range
 *
 * 再平衡策略:如果其中一个消费者退出，经过超时时间45s之后，会将退出的消费者的分区任务分配到其他消费者
 */
public class CustomConsumerMember02 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 如果设置同一个组名，就会形成一个消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
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
