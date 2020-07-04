package com.ct.mapper.ForegroundMapper;

import com.ct.model.ForegroundModel.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    //获取最新上架商品
    List<Product> selectByNew();
    //获取热门商品
    List<Product> selectByHot(int typeid);
    //获取商品详情
    Map<String,Object> selectProductById(int id);
    //获取所属二级分类商品列表
    List<Product> selectScProductById(Map<String, Object> param);
    //发布商品
    int insertProductByUser(Product product);
    //查询商品评论
    List<Map<String,Object>> selectCommentById(int id);
    //添加商品进收藏
    int insertProductToLove(Map<String, Object> param);
    //查询商品是否收藏
    int selectLoveById(Map<String, Object> param);
    //用户发布评论
    int insertComment(Map<String, Object> param);
    //查询用户收藏列表
    List<Product> selectMyCollectById(int id);
    //查询我的商品
    List<Map<String,Object>> selectMyProductById(int id);
    //修改商品数据
    int updateProductByUser(Product product);
    //删除商品信息
    int deleteProductById(int id);
    //取消收藏方法
    int deleteProductToLove(Map<String, Object> param);
    //评论的屏蔽与解屏蔽
    int updateCommentById(Map<String, Object> param);
    //下架以出售商品
    int soldOutProduct();
    //查询官方推荐商品
    List<Product> selectByCommend();
    //修改我的商品售出状态
    int updateOrderProductStatus(Map<String, Object> map);
}
