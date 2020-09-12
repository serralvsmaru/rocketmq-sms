package com.konosuba.rocketmq.sms.consumer.utils;

/**
 * 生成验证码
 *
 * @author konosuba
 */
public class VerificationCodeUtils {

    public static String getVerificationCode(){
        // 创建一个int数组，用来存储生成的验证码
        int[] verCode = new int[6];
        // 生成0-9的随机数组作为验证码
        for (int i = 0; i < verCode.length; i++){
            verCode[i] = (int)(Math.random() * 10);
        }
        // 自定义一个字符缓冲区
        StringBuffer str = new StringBuffer();
        // 遍历int数组，将数组中的元素转化为字符串并储存进缓冲区
        for (int i = 0; i < verCode.length; i++){
            str.append(verCode[i]);
        }
        // 返回缓冲区的字符串的toString()
        return str.toString();
    }
}
