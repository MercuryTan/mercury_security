package com.tx.security.broswer.pojo;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class LoginErrorMsg {

    private Object msg;

    public LoginErrorMsg(Object msg) {
        this.msg = msg;
    }



    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
