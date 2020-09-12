package com.konosuba.rocketmq.sms.consumer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.konosuba.redis.provider.service.RedisService;
import com.konosuba.rocketmq.sms.consumer.config.SmsConfig;
import com.konosuba.rocketmq.sms.consumer.domain.dto.PhoneDto;
import com.konosuba.rocketmq.sms.consumer.service.SmsService;
import com.konosuba.rocketmq.sms.consumer.utils.VerificationCodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 发送短信实现类
 *
 * @author konosuba
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisService redisService;
    @Resource
    private SmsConfig smsConfig;
    @Override
    @StreamListener("input")
    public void smsSend(PhoneDto phoneDto) throws Exception {
        // 验证码
        String verificationCode = VerificationCodeUtils.getVerificationCode();
        //将验证码转化为JSON格式
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", verificationCode);
        // 验证收件人手机号
        String phone = phoneDto.getPhone();
        if (!phone.matches("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$")){
            throw new Exception("手机号错误");
        }
        // 判断是否频繁发送
        if (redisService.get(phone) != null){
            throw new Exception("发送频繁");
        }
        //初始化ascClient,暂时不支持多region（请勿修改）
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        // 组装请求对象
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.setSysRegionId("cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsConfig.getSignName());
        request.putQueryParameter("TemplateCode", smsConfig.getTemplateCode());
        request.putQueryParameter("TemplateParam", smsJson.toJSONString());
        // 发送失败不会再次尝试
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置过期时间
        int timeout = 60;
        // 将验证码存储到redis
        redisService.set(phone, verificationCode, timeout, TimeUnit.SECONDS);
    }
}
