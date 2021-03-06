package com.tx.security.validate.image;


import org.springframework.security.core.AuthenticationException;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class ImageCodeException extends AuthenticationException {

    private String msg;

    public ImageCodeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
