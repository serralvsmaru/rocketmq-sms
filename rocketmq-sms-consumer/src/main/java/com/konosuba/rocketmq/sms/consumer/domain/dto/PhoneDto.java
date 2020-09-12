package com.konosuba.rocketmq.sms.consumer.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 收件者手机号
 *
 * @author konosuba
 */

@Data
public class PhoneDto implements Serializable {
    private String phone;
}
