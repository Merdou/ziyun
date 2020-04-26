package com.hxzy.common.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;

import java.util.Random;

/**
 * 短信发送工具
 */
public class SMSUtil {


    /**
     * 生成验证码
     * @param len  长度(4-7)之间
     * @return
     */
    public static String createCode(int len){
        if(len<4 || len>8){
            len=5;
        }
        StringBuffer str=new StringBuffer();
        Random rd=new Random();
        for(int i=0;i<len;i++) {
            int n = rd.nextInt(10);
            str.append(n);
        }
        return str.toString();
    }

    /**
     * 发送短信
     * @param templateID 模板内容ID
     * @param phoneNumbers 电话号码
     * @param code 验证码
     * @param minute 过期时间
     * @return
     */
    public  static SendSmsResponse sendSMS( String templateID , String[] phoneNumbers,String code,int minute) throws TencentCloudSDKException {

        //(我的账户)app账户
        Credential cred = new Credential("AKIDOgqpcJC9II9QWRs7tgKoZ9Yn6EAwwyKV", "CvTq47zgDZgcMwrhLp9B4m284JPL7jA5");

        //实例化一个短信
        SmsClient smsClient=new SmsClient(cred,"");

        //实例化一个请求对象
        SendSmsRequest req=new SendSmsRequest();
    /* 短信应用ID: 短信SdkAppid在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666 */
        String appid = "1400329587";
        req.setSmsSdkAppid(appid);

    /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
        String sign = "易发公众号";
        req.setSign(sign);

    /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */

        req.setTemplateID(templateID);

      /* 模板参数:  code 代表验证码，minute有期时间(分钟)*/

        String[] templateParams = {code+"",minute+""};
        req.setTemplateParamSet(templateParams);

    /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
       * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
        req.setPhoneNumberSet(phoneNumbers);

    /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
        SendSmsResponse res = smsClient.SendSms(req);

        return res;
    }

















}
