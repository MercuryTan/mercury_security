package com.tx.security.properties;

/**
 *
 */
public class ValidateCodeProperty {

    private ImageCodeProperty imageCode = new ImageCodeProperty();

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