package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Order;
import com.ct.service.ForegroundService.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 工具模块
 */
@RestController
@RequestMapping(value = "/utils")
public class UtilController {
    @Autowired
    private UtilService service;

    /**
     * 查询当天日期是否存在
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectDateFromStatis",method = RequestMethod.POST)
    public Map<String,Object>  selectDateFromStatis(@RequestBody Map<String,Object> map){
        return service.selectDateFromStatis(map);
    }

    /**
     * 添加当日日期信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/insertDateInStatis",method = RequestMethod.POST)
    public int insertDateInStatis(@RequestBody Map<String,Object> map){
        return service.insertDateInStatis(map);
    }

    /**
     * 修改当日点击量和访问量
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateNumInStatis",method = RequestMethod.POST)
    public int updateNumInStatis(@RequestBody Map<String,Object> map){
        return service.updateNumInStatis(map);
    }


    //后台统计模块
    /**
     * 查询商品，用户，订单，评论，交易额
     * @return
     */
    @RequestMapping(value = "/selectDataNum",method = RequestMethod.GET)
    public Map<String,Object> selectDataNum(){
        return service.selectDataNum();
    }

    /**
     * 各个种类发布商品数量统计
     * @return
     */
    @RequestMapping(value = "/selectProductNumByFcid",method = RequestMethod.GET)
    public List<Integer> selectProductNumByFcid(){
        return service.selectProductNumByFcid();
    }

    /**
     * 各个种类商品交易额统计
     * @return
     */
    @RequestMapping(value = "/selectProductMoneyByFcid",method = RequestMethod.GET)
    public List<Integer> selectProductMoneyByFcid(){
        return service.selectProductMoneyByFcid();
    }

    /**
     * 查询当年每月点击量
     * @return
     */
    @RequestMapping(value = "/selectClickNum",method = RequestMethod.GET)
    public List<Object> selectClickNum(String year){
        return service.selectClickNum(year);
    }

    /**
     * 查询当年每月访问量
     * @param year
     * @return
     */
    @RequestMapping(value = "/selectVisitNum",method = RequestMethod.GET)
    public List<Object> selectVisitNum(String year){
        return service.selectVisitNum(year);
    }

}
