package com.ct.mapper.ForegroundMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmailMapper {
    //商品出售，通过id数组查询商品详情，向卖家发送通知
    List<Map<String, String>> selectProductDetail(Map<String,Object> map);
    //订单中商品状态更改，邮件通知
    Map<String, String> selectProductDetailByOrder(Map<String, Object> map);
}
