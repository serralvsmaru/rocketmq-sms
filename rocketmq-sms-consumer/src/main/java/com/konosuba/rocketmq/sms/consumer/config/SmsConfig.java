package com.konosuba.rocketmq.sms.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 封装获取阿里云短信平台的信息
 *
 * @author konosuba
 */
@Component
@Data
public class SmsConfig implements Serializable {
    @Value("${alisms.accessKeyId}")
    private String accessKeyId;
    @Value("${alisms.accessSecret}")
    private String accessKeySecret;
    @Value("${alisms.templateCode}")
    private String templateCode;
    @Value("${alisms.signName}")
    private String signName;
}