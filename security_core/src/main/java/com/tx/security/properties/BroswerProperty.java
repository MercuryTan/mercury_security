package com.tx.security.properties;

import com.tx.security.enumation.JsonType;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
public class BroswerProperty {

    private String loginPage;

    private JsonType jsonType;

    /**
     * cookie的生存时间，默认是3600，可在配置文件中更改
    **/
    private int cookieTime = 3600;

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

    public int getCookieTime() {
        return cookieTime;
    }

    public void setCookieTime(int cookieTime) {
        this.cookieTime = cookieTime;
    }
}
