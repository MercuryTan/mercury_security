package com.tx.security.broswer.pojo;

import com.tx.security.broswer.enumation.JsonType;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class BroswerProperty {

    private String loginPage;

    private JsonType jsonType;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public JsonType getJsonType() {
        return jsonType;
    }

    public void setJsonType(JsonType jsonType) {
        this.jsonType = jsonType;
    }
}
