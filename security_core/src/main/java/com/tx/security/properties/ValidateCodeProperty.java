package com.tx.security.properties;

/**
 * 验证码的配置信息
 */
public class ValidateCodeProperty {

    /**
     * 图片验证码的配置信息
     */
    private ImageCodeProperty imageCode = new ImageCodeProperty();

    /**
     * 短信验证码的配置信息
     */
    private SmsCodeProperty sms = new SmsCodeProperty();


    public ImageCodeProperty getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperty imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperty getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperty sms) {
        this.sms = sms;
    }
}