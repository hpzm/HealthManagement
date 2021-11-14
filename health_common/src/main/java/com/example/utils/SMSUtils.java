package com.example.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    // 短信模板 ID，需要在短信应用中申请
    public static final int LOGIN_CODE = 1200232;//发送短信验证码
    public static final int VALIDATE_CODE = 1200234;//发送短信验证码
    public static final int ORDER_NOTICE = 1200268;//体检预约成功通知
    // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    private static final String smsSign = "小梦为你";
    // 短信应用 SDK AppID 以1400开头
    private static final int appId = 1400595228;
    // 短信应用 SDK AppKey
    private static final String appKey = "c0943090235ff398f16d6f04e0dab141";

    /**
     * 发送短信
     *
     * @param phoneNumbers
     * @param param
     * @throws Exception
     */
    public static void sendShortMessage(int templateCode, String phoneNumbers, String param) throws Exception {
        String[] params={param};
        //https://github.com/qcloudsms/qcloudsms_java
        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
        SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers, templateCode, params, smsSign, "", "");
        System.out.println(result);
        if (result.result == 0 && result.errMsg.equals("OK")) {
            // 请求成功
            System.out.println("请求成功");
        }else {
            System.out.println(result.errMsg);
        }
    }


    public static void main(String[] args) {
        try {
            sendShortMessage(ORDER_NOTICE, "13781598361", "1234");
            //{"result":0,"errmsg":"OK","ext":"","sid":"2645:276056738116367641592052392","fee":1,"isocode":"CN"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
