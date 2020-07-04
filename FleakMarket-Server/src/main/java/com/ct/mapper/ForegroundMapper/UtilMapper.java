package com.ct.mapper.ForegroundMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UtilMapper {
    //查询当天日期是否存在
    Map<String,Object> selectDateFromStatis(Map<String,Object> map);
    //添加当日日期信息
    int insertDateInStatis( Map<String,Object> map);
    //修改当日点击量和访问量
    int updateNumInStatis(Map<String, Object> map);
    //查询网站统计的数据
    int selectProductNum();
    int selectProductSevenNum();

    int selectUserNum();
    int selectUserMoonNum();

    int selectOrderNum();
    int selectOrderSevenNum();

    int selectCommentNum();
    int selectCommentSevenNum();

    int selectMoneyNum();
    int selectMoneyMoonNum();
    //分类获取商品发布量
    List<Integer> selectProductNumByFcid();
    //分类获取商品交易额
    List<Integer> selectProductMoneyByFcid();
    //根据年份获取点击量
    Map<String,Object> selectClickNum(String year);
    //根据年份获取访问量
    Map<String, Object> selectVisitNum(String year);
    //基于注解的延时函数,删除未支付的订单
    void deleteOrderByPay();
}
