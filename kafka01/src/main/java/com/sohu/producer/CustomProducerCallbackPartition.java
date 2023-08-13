package com.sohu.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerCallbackPartition {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer= new KafkaProducer<>(properties);

        for(int i=0; i<5; i++){
            // 生产者指定分区发送数据
            kafkaProducer.send(new ProducerRecord<>("first", 2,"","hellolukang " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("topics "+recordMetadata.topic()+" partition "+recordMetadata.partition());
                    }
                }
            });
        }
        kafkaProducer.close();
    }
}
