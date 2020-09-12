package com.konosuba.rocketmq.sms.provider.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 收件人手机号码
 *
 * @author konosuba
 */

@Data
public class MemberPhoneDto implements Serializable {
    private String phone;
}
