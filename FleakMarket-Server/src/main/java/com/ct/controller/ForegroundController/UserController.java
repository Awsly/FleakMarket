package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Account;
import com.ct.model.ForegroundModel.User;
import com.ct.service.ForegroundService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户输入的用户名与密码
     * @return 返回提示
     */
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public Account toLogin(Account account){
        return userService.toLogin(account);
    }

    /**
     * 通过id获取用户的身份信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectUserById",method = RequestMethod.GET)
    public User selectUserById(Integer id){
        return userService.selectUserById(id);
    }

    /**
     * 通过id修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    public int updateUserById(@RequestBody User user){
        return userService.updateUserById(user);
    }

    /**
     * 通过id查询用户发布商品总量
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectCountById",method = RequestMethod.GET)
    public int selectCountById(Integer id){
        return userService.selectCountById(id);
    }
}
