package com.ct.model.ForegroundModel;

import lombok.Data;

@Data
public class User {
    //用户ID
    private int id;
    //姓名
    private String username;
    //用户头像
    private String userimgpath;
    //手机号码
    private String phonenumber;
    //邮箱
    private String mail;
    //地址
    private String address;
    //生日
    private String birthday;
    //性别
    private String sex;

}
