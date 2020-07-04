package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Product;
import com.ct.service.ForegroundService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台商品模块
 */
@RestController
@RequestMapping(value = "/product")
public class productController {
    @Autowired
    private ProductService proservice;

    /**
     * 查询七天之内发布的商品(最新商品接口）
     * @return
     */
    @RequestMapping(value = "/selectByNew",method = RequestMethod.GET)
    public List<Product> selectByNew(){
        return proservice.selectByNew();
    }

    /**
     * 查询热门商品
     * @param typeid
     * @return
     */
    @RequestMapping(value = "/selectByHot",method = RequestMethod.GET)
    public List<Product> selectByHot(int typeid){
        return proservice.selectByHot(typeid);
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectProductById",method = RequestMethod.GET)
    public Map<String,Object> selectProductById(int id){
        return proservice.selectProductById(id);
    }

    /**
     * 查询所属二级分类商品列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/selectScProductById",method = RequestMethod.POST)
    public List<Product> selectScProductById(@RequestBody Map<String, Object> param){
        return proservice.selectScProductById(param);
    }

    /**
     * 登录用户发布商品
     * @param product
     * @return
     */
    @RequestMapping(value = "/insertProductByUser",method = RequestMethod.POST)
    public int insertProductByUser(@RequestBody Product product){
        return proservice.insertProductByUser(product);
    }

    /**
     * 商品评论列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectCommentById",method = RequestMethod.GET)
    public List<Map<String,Object>> selectCommentById(int id){
        return proservice.selectCommentById(id);
    }

    /**
     * 商品添加收藏
     * @param param
     * @return
     */
    @RequestMapping(value = "/insertProductToLove",method = RequestMethod.POST)
    public int insertProductToLove(@RequestBody Map<String, Object> param){
        return proservice.insertProductToLove(param);
    }

    /**
     * 查询我的收藏是否已存在当前商品
     * @param param
     * @return
     */
    @RequestMapping(value = "/selectLoveById",method = RequestMethod.POST)
    public int selectLoveById(@RequestBody Map<String, Object> param){
        return proservice.selectLoveById(param);
    }

    /**
     * 用户发布评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/insertComment",method = RequestMethod.POST)
    public int insertComment(@RequestBody Map<String, Object> param){
        return proservice.insertComment(param);
    }

    /**
     * 查询我的收藏商品
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectMyCollectById",method = RequestMethod.GET)
    public List<Product> selectMyCollectById(int id){
        return proservice.selectMyCollectById(id);
    }

    /**
     * 我发布的商品列表查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectMyProductById",method = RequestMethod.GET)
    public List<Map<String,Object>> selectMyProductById(int id){
        return proservice.selectMyProductById(id);
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @RequestMapping(value = "/updateProductByUser",method = RequestMethod.POST)
    public int updateProductByUser(@RequestBody Product product){
        return proservice.updateProductByUser(product);
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteProductById",method = RequestMethod.GET)
    public int deleteProductById(int id){
        return proservice.deleteProductById(id);
    }

    /**
     * 取消收藏
     * @param param
     * @return
     */
    @RequestMapping(value = "/deleteProductToLove",method = RequestMethod.POST)
    public int deleteProductToLove(@RequestBody Map<String, Object> param){
        return proservice.deleteProductToLove(param);
    }

    /**
     * 修改评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateCommentById",method = RequestMethod.POST)
    public int updateCommentById(@RequestBody Map<String, Object> param){
        return proservice.updateCommentById(param);
    }

    /**
     * 下架商品
     * @return
     */
    @RequestMapping(value = "/soldOutProduct",method = RequestMethod.GET)
    public int soldOutProduct(){
        return proservice.soldOutProduct();
    }

    /**
     * 商品推荐列表
     */
    @RequestMapping(value = "/selectByCommend",method = RequestMethod.GET)
    public List<Product> selectByCommend(){
        return proservice.selectByCommend();
    }

    /**
     * 修改订单商品状态
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateOrderProductStatus",method = RequestMethod.POST)
    public int updateOrderProductStatus(@RequestBody Map<String,Object> map){
        return proservice.updateOrderProductStatus(map);
    }
}
