package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Order;
import com.ct.service.ForegroundService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台订单接口
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService Service;

    /**
     * 创建订单接口
     * @param order
     * @return
     */
    @RequestMapping(value = "/insertOrderByOrder",method = RequestMethod.POST)
    public Map<String,Object> insertOrderByOrder(@RequestBody Order order){
        return Service.insertOrderByOrder(order);
    }

    /**
     * 通过id查看订单详情接口
     * @param oid
     * @return
     */
    @RequestMapping(value = "/selectOrderById",method = RequestMethod.GET)
    public Order selectOrderById(String oid){
        return Service.selectOrderById(oid);
    }

    /**
     * 修改订单信息接口
     * @param order
     * @return
     */
    @RequestMapping(value = "/updateOrderByOid",method = RequestMethod.POST)
    public int updateOrderByOid(@RequestBody Order order){
        return Service.updateOrderByOid(order);
    }

    /**
     * 删除订单接口
     * @param order
     * @return
     */
    @RequestMapping(value = "/deleteOrderByOid",method = RequestMethod.POST)
    public int deleteOrderByOid(@RequestBody Order order){
        return Service.deleteOrderByOid(order);
    }

    /**
     * 查询个人订单列表接口
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selectOrderByUserId",method = RequestMethod.GET)
    public List<Order> selectOrderByUserId(int userid){
        return Service.selectOrderByUserId(userid);
    }

    /**
     * 后台--查询所有订单列表接口
     * @param order
     * @return
     */
    @RequestMapping(value = "/selectOrderByList",method = RequestMethod.POST)
    public List<Order> selectOrderByList(@RequestBody Order order){
        return Service.selectOrderByList(order);
    }

    /**
     * 查询个人订单商品列表接口
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selectOrderProductByUserId",method = RequestMethod.GET)
    public List<Map<String,Object>> selectOrderProductByUserId(int userid){
        return Service.selectOrderProductByUserId(userid);
    }

    /**
     * 修改订单商品的状态
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateOrderById",method = RequestMethod.POST)
    public int updateOrderById(@RequestBody Map<String,Object> map){
        return Service.updateOrderById(map);
    }
}
