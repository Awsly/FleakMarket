package com.ct.model.ForegroundModel;

import java.util.Date;

/**
 * 商品实体类
 */
public class Product {
    //商品id
    private int id;
    //商品名称
    private String name;
    //商品描述
    private String details;
    //商品图片
    private String images;
    //商品点击量
    private String clicks;
    //商品创建时间
    private String creattime;
    //商品原价
    private String originalprice;
    //商品现价
    private String currentprice;
    //交易方式
    private String deal;
    //交易地址
    private String address;
    //商品留言
    private String comments;
    //结束时间
    private String status;
    //商品类别
    private int sid;
    //发布人id
    private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(String originalprice) {
        this.originalprice = originalprice;
    }

    public String getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(String currentprice) {
        this.currentprice = currentprice;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", images='" + images + '\'' +
                ", clicks='" + clicks + '\'' +
                ", creattime='" + creattime + '\'' +
                ", originalprice='" + originalprice + '\'' +
                ", currentprice='" + currentprice + '\'' +
                ", deal='" + deal + '\'' +
                ", address='" + address + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                ", sid=" + sid +
                ", uid=" + uid +
                '}';
    }
}
