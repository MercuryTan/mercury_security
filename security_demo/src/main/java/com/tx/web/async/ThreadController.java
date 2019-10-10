package com.tx.web.async;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：tx
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    OrderQueue orderQueue;

    @Autowired
    DeferredResultHanlder deferredResultHanlder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //callable
    @GetMapping
    public String callable() throws Exception {
        logger.info("主线程开始了。。。");
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                logger.info("副线程开始了。。。");
                //执行业务代码
                Thread.sleep(1000);
                logger.info("副线程结束了。。。");
                return 1;
            }
        };
        FutureTask futureTask = new FutureTask(callable);

        Thread thread = new Thread(futureTask);
        thread.start();

        logger.info("主线程结束了。。。");

        System.out.println("副线程返回的值是："+futureTask.get());
        return "hello world";
    }

    /**
     * 用户提交订单到队列中，线程2处理线程，把结果放回队列中，
     * 线程3获取队列中的值，返回给用户
     * @return
     */
    @GetMapping("/deffer")
    public DeferredResult<String> threadRun(){
        logger.info("主线程开始了。。。");
        //发起订单
        String orderNum = RandomStringUtils.randomNumeric(8);
        orderQueue.setPlaceOrder(orderNum);
        //返回结果
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHanlder.getMap().put(orderNum,result);
        logger.info("主线程结束了。。。");
        return result;
    }

}
