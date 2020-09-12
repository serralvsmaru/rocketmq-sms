# rocketmq-sms

#### 介绍
RocketMQ 通过阿里云平台发送短信

#### 使用说明

本项目只是个人学习使用

1. 修改`sms-consumer`内的`application.yml`里的配置
2. 修改项目中的redis、RocketMQ等配置
3. 依次启动`sms-provider`和`sms-consumer`
4. 通过postman用post请求访问http://127.0.0.1:9091/provider/sms，请求的Json数据为{"phone" : "发送短信的号码"}
5. 手机接收到短信，发送成功。

