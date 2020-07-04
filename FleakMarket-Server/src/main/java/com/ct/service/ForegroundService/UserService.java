package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.UserMapper;
import com.ct.model.ForegroundModel.Account;
import com.ct.model.ForegroundModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper usermapper;

    public Account toLogin(Account account) {
        if(account.getUsername().isEmpty()){
            return null;
        }
        return usermapper.toLogin(account);
    }

    public User selectUserById(int id) {
        if(id == 0){
            return null;
        }
        return usermapper.selectUserById(id);
    }

    public int updateUserById(User user) {
        if(user.getId() == 0){
            return -1;
        }
        return usermapper.updateUserById(user);
    }

    public int selectCountById(Integer id) {
        if(id == null){
            return -1;
        }
        return usermapper.selectCountById(id);
    }

    public int deleteUserById(Integer id) {
        return usermapper.deleteUserById(id);
    }

    public int restAccountById(Integer id) {
        return usermapper.restAccountById(id);
    }

    public int insertUserInfo(User user) {
        usermapper.insertUserInfo(user);
        return user.getId();
    }

    public int insertUserToAccount(Account account) {
        return usermapper.insertUserToAccount(account);
    }

    public int selectCartCountById(int id) {
        return usermapper.selectCartCountById(id);
    }

    public int selectCommentById(int id) {
        return usermapper.selectCommentById(id);
    }

    public Account selectAccountById(int id) {
        return usermapper.selectAccountById(id);
    }
}
