package com.tx.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.tx.validation.MyValidation;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class User implements Serializable {

    public interface CommonJsonView{

    }

    public interface UserInfoJsonView extends CommonJsonView{

    }

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotNull
    private String password;
    private String id;

    @MyValidation(message = "这是我的校验注解呀")
    private Date birthday;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String id, Date birthday) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.birthday = birthday;
    }

    @JsonView(CommonJsonView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserInfoJsonView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}