package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.UtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilService {

    @Autowired
    private UtilMapper mapper;

    /**
     * 查询当天日期是否存在
     * @param map
     * @return
     */
    public Map<String,Object>  selectDateFromStatis(Map<String,Object> map) {
        return mapper.selectDateFromStatis(map);
    }

    /**
     * 添加当日日期信息
     * @param map
     * @return
     */
    public int insertDateInStatis( Map<String,Object> map) {
        return mapper.insertDateInStatis(map);
    }

    /**
     * 修改当日点击量和访问量
     * @param map
     * @return
     */
    public int updateNumInStatis(Map<String, Object> map) {
        return mapper.updateNumInStatis(map);
    }

    /**
     * 查询网站统计的数据
     * @return
     */
    public Map<String,Object> selectDataNum() {
        //后台统计模块数据
        int productSevenNum = mapper.selectProductSevenNum();
        int productTotalNum = mapper.selectProductNum();
        int userMoonNum = mapper.selectUserNum();
        int userTotalNum = mapper.selectUserMoonNum();
        int orderSevenNum = mapper.selectOrderSevenNum();
        int orderTotalNum = mapper.selectOrderNum();
        int commentSevenNum = mapper.selectCommentSevenNum();
        int commentTotalNum = mapper.selectCommentNum();
        int moneyMoonNum = mapper.selectMoneyMoonNum();
        int moneyTotalNum = mapper.selectMoneyNum();


        Map<String,Object> map = new HashMap<>();
        map.put("productSevenNum",productSevenNum);
        map.put("productTotalNum",productTotalNum);
        map.put("userMoonNum",userMoonNum);
        map.put("userTotalNum",userTotalNum);
        map.put("orderSevenNum",orderSevenNum);
        map.put("orderTotalNum",orderTotalNum);
        map.put("commentSevenNum",commentSevenNum);
        map.put("commentTotalNum",commentTotalNum);
        map.put("moneyMoonNum",moneyMoonNum);
        map.put("moneyTotalNum",moneyTotalNum);
        return map;
    }

    /**
     * 分类获取商品发布量
     * @return
     */
    public List<Integer> selectProductNumByFcid() {
        return mapper.selectProductNumByFcid();
    }

    /**
     * 分类获取商品交易额
     * @return
     */
    public List<Integer> selectProductMoneyByFcid() {
        return mapper.selectProductMoneyByFcid();
    }

    /**
     * 根据年份获取点击量
     * @param year
     * @return
     */
    public List<Object> selectClickNum(String year) {
        Map<String,Object> map = mapper.selectClickNum(year);
        List<Object> list = new ArrayList<>();
        for(int i=1;i<13;i++){
            list.add(map.get(String.valueOf(i)));
        }
        return list;
    }

    //根据年份获取访问量
    public List<Object> selectVisitNum(String year) {
        Map<String,Object> map = mapper.selectVisitNum(year);
        List<Object> list = new ArrayList<>();
        for(int i=1;i<13;i++){
            list.add(map.get(String.valueOf(i)));
        }
        return list;
    }

    /**
     * 基于注解的延时函数
     */
    @Async
    @Scheduled(cron = "0 */15 * * * ?")
    public void Times(){
        mapper.deleteOrderByPay();
    }

}
