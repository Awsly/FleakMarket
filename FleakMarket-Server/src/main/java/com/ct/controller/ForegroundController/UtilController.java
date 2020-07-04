package com.ct.controller.ForegroundController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ct.config.AliPayConfig;
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

    /**
     * 订单支付接口
     * @param
     * @return
     */
    @RequestMapping(value = "/orderPay",method = RequestMethod.GET)
    public String orderPay(Order order) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AliPayConfig.gatewayUrl,
                AliPayConfig.APP_ID,
                AliPayConfig.APP_PRIVATE_KEY,
                "json",
                AliPayConfig.CHARSET,
                AliPayConfig.ALIPAY_PUBLIC_KEY,
                AliPayConfig.sign_type
        );


        //设置请求参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();


        request.setReturnUrl(AliPayConfig.return_url);
        request.setNotifyUrl(AliPayConfig.notify_url); //在公共参数中设置回跳和通知地址

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOid();
        //付款金额，必填
        String total_amount =order.getProducttotal();
        //订单名称，必填
        String subject = "唐院二手交易平台";
        //商品描述，可空
        String body = order.getRemark();

        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);

        return response.getBody();
    }
}
