package com.tx.security.domain;

import java.time.LocalDateTime;

/**
 *
 */
public class ValidateCode {

    private String code;

    private LocalDateTime failureTime;

    /**
     * @author tx
     * @description
     * @Param: code  验证码
     * @Param: effectiveTime  失效时间
     * @return
     **/
    public ValidateCode( String code, int effectiveTime) {
        this.code = code;
        this.failureTime = LocalDateTime.now().plusSeconds(effectiveTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(LocalDateTime failureTime) {
        this.failureTime = failureTime;
    }
}