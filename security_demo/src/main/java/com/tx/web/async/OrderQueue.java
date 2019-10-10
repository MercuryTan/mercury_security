package com.tx.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class OrderQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String placeOrder;

    private String completeOrder;

    private String isHandler = "0";

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        this.placeOrder = placeOrder;
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }

    public String getIsHandler() {
        return isHandler;
    }

    public void setIsHandler(String isHandler) {
        this.isHandler = isHandler;
    }
}