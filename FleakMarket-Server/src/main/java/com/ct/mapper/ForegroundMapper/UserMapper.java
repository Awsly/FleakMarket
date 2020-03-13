package com.ct.mapper.ForegroundMapper;

import com.ct.model.ForegroundModel.Account;
import com.ct.model.ForegroundModel.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //通过用户名密码查询是否存在此用户
    Account toLogin(Account account);
    //通过id查询当前用户信息
    User selectUserById(int id);
    //通过id修改当前用户身份信息
    int updateUserById(User user);
    //通过id查询用户发布商品数量
    int selectCountById(Integer id);
}