package com.app;

import com.app.model.Supplier;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@SpringBootApplication
public class KafkaCustomObjectProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaCustomObjectProducerApplication.class, args);

		String topicName="supplier-topic";

		Properties prop = new Properties();
		prop.put("bootstrap.servers","localhost:9092");
		prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		prop.put("value.serializer","com.app.service.SupplierSerializer");

		Producer<String, Supplier> producer = new KafkaProducer<>(prop);
        try {

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Supplier s1 = new Supplier(1,"abc", df.parse("2024-04-01"));
			Supplier s2 = new Supplier(2,"xyz", df.parse("2024-02-14"));
            producer.send(new ProducerRecord<String,Supplier>(topicName,s1));
            producer.send(new ProducerRecord<String,Supplier>(topicName,s2));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
