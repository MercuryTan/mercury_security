package com.tx.security.broswer.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@Component
@ConfigurationProperties(prefix = "mercury")
@PropertySource("classpath:mercury.properties")
public class MercuryProperty {

    private BroswerProperty broswer;

    public BroswerProperty getBroswer() {
        return broswer;
    }

    public void setBroswer(BroswerProperty broswer) {
        this.broswer = broswer;
    }
}
