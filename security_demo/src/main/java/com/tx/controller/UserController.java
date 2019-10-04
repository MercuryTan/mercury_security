package com.tx.controller;

import com.tx.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> queryAll(){
        List<User> users = new ArrayList<>();
        users.add(new User("mercury","111111"));
        users.add(new User("stt","111111"));
        users.add(new User("tx","111111"));
        return users;
    }
}