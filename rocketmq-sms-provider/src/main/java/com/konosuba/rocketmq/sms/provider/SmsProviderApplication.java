package com.konosuba.rocketmq.sms.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 启动类
 *
 * @author konosuba
 */

@SpringBootApplication
@EnableBinding(Source.class)
public class SmsProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsProviderApplication.class, args);
    }
}
