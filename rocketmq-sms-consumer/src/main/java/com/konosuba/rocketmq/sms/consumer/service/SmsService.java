package com.konosuba.rocketmq.sms.consumer.service;

import com.konosuba.rocketmq.sms.consumer.domain.dto.PhoneDto;

/**
 *
 *
 * @author konosuba
 */
public interface SmsService {
    /**
     * 发送短信
     * @param phoneDto JavaBean
     */
    void smsSend(PhoneDto phoneDto) throws Exception;
}
