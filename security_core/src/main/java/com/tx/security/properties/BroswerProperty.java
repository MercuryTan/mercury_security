package com.tx.security.properties;

import com.tx.security.domain.SecurityConstants;
import com.tx.security.enumation.JsonType;

/**
 * @author ：tx
 * @description：浏览器应用的配置信息
 * @modified By：
 * @version:
 */
public class BroswerProperty {

    /**
     * 登录页面-- .html
    **/
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**
     *  类型： jsonType :JSON -- 输出json
     *                   REDIRECT -- 跳转到loginPage上
     *
     *  默认json,可从配置文件中覆盖
     **/
    private JsonType jsonType = JsonType.JSON;

    /**
     * cookie的生存时间，默认是3600，可在配置文件中更改
    **/
    private int cookieTime = SecurityConstants.DEFAULT_LOGIN_REMEMBER_ME_TIME;

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
