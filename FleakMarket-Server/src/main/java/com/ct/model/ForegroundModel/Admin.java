package com.ct.model.ForegroundModel;

import lombok.Data;

@Data
public class Admin {
    //管理员id
    private int id;
    //管理员用户名
    private String username;
    //管理员密码
    private String password;
    //管理员联系方式
    private String phone;
    //管理员真实姓名
    private String tname;
    //管理员最后登录时间
    private String logintime;
    //管理员账户级别
    private String lv;
}
