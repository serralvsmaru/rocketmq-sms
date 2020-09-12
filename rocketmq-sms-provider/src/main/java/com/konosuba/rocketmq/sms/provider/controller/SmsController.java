package com.konosuba.rocketmq.sms.provider.controller;

import com.konosuba.rocketmq.sms.provider.domain.dto.MemberPhoneDto;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发送短信的接口
 *
 * @author konosuba
 */
@RestController
@RequestMapping("/provider")
public class SmsController {

    @Resource(name = "output")
    private MessageChannel messageChannel;

    @PostMapping("/sms")
    public Boolean smsSend(@RequestBody MemberPhoneDto memberPhoneDto){
        return messageChannel.send(MessageBuilder.withPayload(memberPhoneDto).build());
    }
}
