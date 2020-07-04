package com.ct.model.ForegroundModel;

import lombok.*;

import java.util.List;

@Data
public class Order {

    //订单id
    private int id;
    //订单编号
    private String oid;
    //创建人编号
    private int cid;
    //创建人
    private String createperson;
    //创建时间
    private String createtime;
    //是否支付
    private String pay;
    //支付时间
    private String paytime;
    //商品数量
    private String productnumber;
    //商品总价
    private String producttotal;
    //收货人
    private String consignee;
    //收货人联系方式
    private String phone;
    //收货人邮箱
    private String mail;
    //收货地址
    private String address;
    //订单备注
    private String remark;

    //商品id
    private List<Integer> pid;
    //商品列表
    private List<Product> products;
}
