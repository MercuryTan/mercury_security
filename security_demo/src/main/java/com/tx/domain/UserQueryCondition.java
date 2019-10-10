package com.tx.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * user查询参数
 */
public class UserQueryCondition {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户起始年龄")
    private int age;

    @ApiModelProperty(value = "用户结束年龄")
    private int ageTo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}