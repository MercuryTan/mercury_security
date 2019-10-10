package com.tx.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 线程3从队列中拿出完成的订单信息
 */
@Component
public class ConsumeListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    OrderQueue orderQueue;

    @Autowired
    DeferredResultHanlder deferredResultHanlder;

    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while(true){
                String isHandler = orderQueue.getIsHandler();
                if("2".equals(isHandler)){ //消息队列中有已完成的订单
                    try {
                        String orderNum = orderQueue.getPlaceOrder();
                        logger.info("线程3正在获取完成的订单信息");
                        Thread.sleep(1000);
                        logger.info("线程3获取完成的订单信息，订单号为：" + orderNum);
                        orderQueue.setIsHandler("3");//该条消息已处理过
                        deferredResultHanlder.getMap().get(orderNum).setResult("订单完成成功");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}