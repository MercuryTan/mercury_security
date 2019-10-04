package com.tx.controller;

import com.tx.domain.User;
import com.tx.domain.UserQueryCondition;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> queryAll(
//            @RequestParam(name = "userName",required = false,defaultValue = "hhhhhh") String nickName,
            UserQueryCondition condition
            ){

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User("mercury","111111"));
        users.add(new User("stt","111111"));
        users.add(new User("tx","111111"));
        return users;
    }
}