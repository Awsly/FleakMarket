package com.ct.controller.BackgroundController;

import com.ct.model.ForegroundModel.Product;
import com.ct.service.BackgroundService.BProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 后台商品模块
 */
@RestController
@RequestMapping(value = "/Bproduct")
public class BProductController {

    @Autowired
    private BProductService Service;

    /**
     * 查询所有商品列表接口
     * @param pro
     * @return
     */
    @RequestMapping(value = "/selectProductList",method = RequestMethod.POST)
    public List<Map<String,Object>> selectProductList(@RequestBody Product pro){
        return Service.selectProductList(pro);
    }

    /**
     * 宣传商品接口
     * @param pid
     * @return
     */
    @RequestMapping(value = "/insertRecommendByPid",method = RequestMethod.GET)
    public int insertRecommendByPid(String pid){
        return Service.insertRecommendByPid(pid);
    }

    /**
     * 撤销宣传接口
     * @param pid
     * @return
     */
    @RequestMapping(value = "/deleteRecommendByPid",method = RequestMethod.GET)
    public int deleteRecommendByPid(String pid){
        return Service.deleteRecommendByPid(pid);
    }


    /**
     * 查询所有商品列表接口
     * @param pro
     * @return
     */
    @RequestMapping(value = "/selectProductCommentList",method = RequestMethod.POST)
    public List<Map<String,Object>> selectProductCommentList(@RequestBody Product pro){
        return Service.selectProductCommentList(pro);
    }

}
