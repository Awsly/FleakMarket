package com.ct.mapper.BackgroundMapper;

import com.ct.model.ForegroundModel.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BProductMapper {
    //查询全部商品列表
    List<Map<String,Object>> selectProductList(Product pro);
    //添加首页推荐
    int insertRecommendByPid(String pid);
    //取消首页推荐
    int deleteRecommendByPid(String pid);
    //查询商品评论列表
    List<Map<String, Object>> selectProductCommentList(Product pro);
}
