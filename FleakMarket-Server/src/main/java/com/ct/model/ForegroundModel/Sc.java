package com.ct.model.ForegroundModel;

public class Sc {
    //二级分类id
    private int id;
    //二级分类名称
    private String name;
    //二级分类地址
    private String path;
    //所属一级分类id
    private int fcid;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFcid() {
        return fcid;
    }

    public void setFcid(int fcid) {
        this.fcid = fcid;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", fcid=" + fcid +
                '}';
    }
}
