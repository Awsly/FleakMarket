package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Product;
import com.ct.service.ForegroundService.CartService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台购物车模块
 */
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService Service;

    /**
     * 查询用户购物车列表数据
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selectCartById",method = RequestMethod.GET)
    public List<Product> selectCartById(int userid){
        return Service.selectCartById(userid);
    }

    /**
     * 批量删除购物车商品数据
     * @param map
     * @return
     */
    @RequestMapping(value = "/deleteProductByArray",method = RequestMethod.POST)
    public int deleteProductByArray(@RequestBody Map<String,Object> map){
        return Service.deleteProductByArray(map);
    }

    /**
     * 通过uid,pid查询购物车是否存在该商品
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectCartProductById",method = RequestMethod.POST)
    public int deleteCartById(@RequestBody Map<String,Object> map){
        return Service.selectCartProductById(map);
    }

    /**
     * 向购物车中插入数据
     * @param map
     * @return
     */
    @RequestMapping(value = "/insertCartById",method = RequestMethod.POST)
    public int insertCartById(@RequestBody  Map<String,Object> map){
        return Service.insertCartById(map);
    }
}
