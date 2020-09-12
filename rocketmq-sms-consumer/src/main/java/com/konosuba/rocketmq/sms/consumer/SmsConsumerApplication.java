package com.konosuba.rocketmq.sms.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 启动类
 *
 * @author konosuba
 */
@SpringBootApplication(scanBasePackages = {"com.konosuba.redis.provider", "com.konosuba.rocketmq.sms.consumer"})
@EnableBinding(Sink.class)
public class SmsConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsConsumerApplication.class, args);
    }
}
