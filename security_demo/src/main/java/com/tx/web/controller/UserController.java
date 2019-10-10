package com.tx.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.tx.domain.User;
import com.tx.domain.UserQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/*import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;*/
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户控制器")
public class UserController {

    @JsonView(User.CommonJsonView.class)
    @GetMapping
    @ApiOperation(value = "获取所有用户信息" , notes = "返回的是所有用户")
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

    @JsonView(User.UserInfoJsonView.class)
    @GetMapping("/getInfo/{id:\\d+}/{age}")
    public User getOneInfo(@PathVariable(name="id") String userId,@PathVariable String age){
        System.out.println(userId);
        System.out.println(age);
        User user = new User("mercury","111111");
        return user;
    }

//    @JsonView(User.CommonJsonView.class)
    @PostMapping
    public User createUser(@RequestBody @Valid User user, BindingResult errors){
        if(errors.hasErrors()){ // 保存错误
            List list = errors.getAllErrors();
            System.out.println("have error");
        }
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        user.setBirthday(new Date());
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User updateUser(@RequestBody @Valid User user,BindingResult errors,@PathVariable String id
                          ){
        System.out.println("当前操作的用户id为："+id);
        if(errors.hasErrors()){ // 保存错误
           errors.getAllErrors().stream().forEach(error ->{
              /* FieldError fieldError = (FieldError)error;
               System.out.println(fieldError.getField()+" --"+fieldError.getDefaultMessage());*/
               System.out.println(error.getDefaultMessage());
           } );
        }
        //调用修改的操作
        user.setId(id);
        return user;
    }

    @GetMapping("/{id:\\d+}")
    public User exceptionTest(@ApiParam("用户id") @PathVariable String id){
//        throw new UserNotExistException(id);
        return new User("mercury","11111");
    }
}
