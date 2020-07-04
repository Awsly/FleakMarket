package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Account;
import com.ct.model.ForegroundModel.User;
import com.ct.service.ForegroundService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台用户信息模块
 */
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

    /**
     * 通过id删除账户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.GET)
    public int deleteUserById(Integer id){
        return userService.deleteUserById(id);
    }

    /**
     * 重置用户账户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/restAccountById",method = RequestMethod.GET)
    public int restAccountById(Integer id){
        return userService.restAccountById(id);
    }

    /**
     * 添加用户信息接口
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUserInfo",method = RequestMethod.POST)
    public int insertUserInfo(@RequestBody User user){
        return userService.insertUserInfo(user);
    }

    /**
     * 添加用户密码接口
     * @param account
     * @return
     */
    @RequestMapping(value = "/insertUserToAccount",method = RequestMethod.POST)
    public int insertUserToAccount(@RequestBody Account account){
        return userService.insertUserToAccount(account);
    }

    /**
     *查询用户购物车数量接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectCartCountById",method = RequestMethod.GET)
    public int selectCartCountById(int id){
        return userService.selectCartCountById(id);
    }

    /**
     * 查询用户评论数量接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectCommentById",method = RequestMethod.GET)
    public int selectCommentById(int id){
        return userService.selectCommentById(id);
    }

    /**
     * 通过id查询账户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectAccountById",method = RequestMethod.GET)
    public Account selectAccountById(int id){
        return userService.selectAccountById(id);
    }

}
