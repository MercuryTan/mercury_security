package com.tx.web.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 查看消息队列中是否有订单号
 */
@Component
public class HandlerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    OrderQueue orderQueue;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while(true){
                String orderNumber = orderQueue.getPlaceOrder();
                String isHandle = orderQueue.getIsHandler();
                if(StringUtils.isNoneBlank(orderNumber) && "0".equals(isHandle)){ //消息队列中有订单号
                    //处理订单
                    try {
                        orderQueue.setCompleteOrder(null);
                        logger.info("线程2正在处理订单");
                        orderQueue.setIsHandler("1"); //表示该条订单正在处理
                        Thread.sleep(1000);
                        orderQueue.setIsHandler("2"); //订单处理完毕
                        logger.info("线程2处理订单完毕，订单号为：" + orderNumber);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}