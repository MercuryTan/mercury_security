package com.tx.domain;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 *
 */
public class User implements Serializable {

    public interface CommonJsonView{

    }

    public interface UserInfoJsonView extends CommonJsonView{

    }



    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}