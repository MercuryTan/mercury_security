package com.tx.security.properties;

/**
 *
 */
public class ImageCodeProperty {

    /**
     * 图片的宽度
     */
    private int width = 67;

    /**
     * 图片的高度
     */
    private int height = 23;

    /**
     * 验证码的长度
     */
    private int codeLength = 4;

    /**
     * 验证码有效时间
     */
    private int expireTime = 60;

    /**
     * 需要校验验证码的地址
     */
    private String urls;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}