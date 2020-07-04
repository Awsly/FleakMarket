package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.OrderMapper;
import com.ct.model.ForegroundModel.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderMapper mapper;


    public Map<String,Object> insertOrderByOrder(Order order) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> list = new ArrayList<>();

        //生成UUID作为订单编号
        String uuid = UUID.randomUUID().toString().toUpperCase();
        order.setOid(uuid);
        for (Object item : order.getPid()) {
            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("oid",uuid);
            map2.put("pid",item);
            list.add(map2);
        }
        //创建订单
        int resulto =  mapper.insertOrderByOrder(order);
        //插入订单商品列表
        int resultp = mapper.insertOrderProduct(list);
        map.put("resulto",resulto);
        map.put("resultp",resultp);
        map.put("uuid",uuid);
        return map;
    }

    public Order selectOrderById(String oid) {
        return mapper.selectOrderById(oid);
    }

    public int updateOrderByOid(Order order) {
        return mapper.updateOrderByOid(order);
    }

    public int deleteOrderByOid(Order order) {
        return mapper.deleteOrderByOid(order);
    }

    public List<Order> selectOrderByUserId(int userid) {
        return mapper.selectOrderByUserId(userid);
    }

    public List<Order> selectOrderByList(Order order) {
        return mapper.selectOrderByList(order);
    }

    public List<Map<String, Object>> selectOrderProductByUserId(int userid) {
        return mapper.selectOrderProductByUserId(userid);
    }

    public int updateOrderById(Map<String,Object> map) {
        return mapper.updateOrderById(map);
    }
}
