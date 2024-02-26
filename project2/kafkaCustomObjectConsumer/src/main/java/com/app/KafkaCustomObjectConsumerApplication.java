package com.app;

import com.app.model.Supplier;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class KafkaCustomObjectConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaCustomObjectConsumerApplication.class, args);

		String topicName = "supplier-topic";
		String groupName = "SupplierTopicGroup";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", groupName);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.app.service.SupplierDeserializer");
		props.put("auto.offset.reset", "earliest");


		KafkaConsumer<String, Supplier> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topicName));

		while (true){
			ConsumerRecords<String, Supplier> records = consumer.poll(100);
			for (ConsumerRecord<String, Supplier> record : records){
				System.out.println("Supplier id= " + String.valueOf(record.value().getSuppilerId()) + " Supplier  Name = " + record.value().getSupplierName() + " Supplier Start Date = " + record.value().getSupplierStartDate().toString());
			}
		}

	}

}
