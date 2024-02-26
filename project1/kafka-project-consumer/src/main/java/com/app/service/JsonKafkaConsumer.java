package com.app.service;

import com.app.payload.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    @KafkaListener(topics = "myUserTopic", groupId = "myGroup")
    public void consume(User user)
    {
        System.out.println(user);
    }

}
