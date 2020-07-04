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
    //通过id删除用户信息
    int deleteUserById(Integer id);
    //重置账户
    int restAccountById(Integer id);
    //用户注册
    int insertUserInfo(User user);
    //插入用户账户信息到账户表中
    int insertUserToAccount(Account account);
    //通过id查询用户购物车商品数量
    int selectCartCountById(int id);
    //通过id查询用户发布评论数量
    int selectCommentById(int id);
    //通过id查询账户信息
    Account selectAccountById(int id);
}