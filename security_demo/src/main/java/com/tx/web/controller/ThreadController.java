package com.tx.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

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

}
