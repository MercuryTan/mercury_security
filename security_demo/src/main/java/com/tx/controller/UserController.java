package com.tx.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.tx.domain.User;
import com.tx.domain.UserQueryCondition;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/*import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;*/
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @JsonView(User.CommonJsonView.class)
    public List<User> queryAll(
//            @RequestParam(name = "userName",required = false,defaultValue = "hhhhhh") String nickName,
            UserQueryCondition condition/*,
            Pageable pageable*/
            ){

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        //分页参数 jpa  要先配置数据库
       /* System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());*/
        List<User> users = new ArrayList<>();
        users.add(new User("mercury","111111"));
        users.add(new User("stt","111111"));
        users.add(new User("tx","111111"));
        return users;
    }

    @RequestMapping("/user/getInfo/{id:\\d+}/{age}")
    @JsonView(User.UserInfoJsonView.class)
    public User getOneInfo(@PathVariable(name="id") String userId,@PathVariable String age){
        System.out.println(userId);
        System.out.println(age);
        User user = new User("mercury","111111");
        return user;
    }
}
