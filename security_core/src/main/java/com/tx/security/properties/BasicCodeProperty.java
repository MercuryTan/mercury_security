package com.tx.security.properties;

import com.tx.security.domain.SecurityConstants;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class BasicCodeProperty {

    /**
     * 验证码的长度
     */
    private int codeLength = SecurityConstants.CODE_LENGTH;

    /**
     * 验证码有效时间
     */
    private int expireTime = SecurityConstants.CODE_EXPIRE_TIME;

    /**
     * 需要校验验证码的地址
     */
    private String urls;

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
