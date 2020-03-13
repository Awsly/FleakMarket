package com.ct.model.ForegroundModel;

public class User {
    //用户ID
    private int id;
    //姓名
    private String username;
    //用户头像
    private String userimgpath;
    //手机号码
    private String phonenumber;
    //邮箱
    private String mail;
    //地址
    private String address;
    //生日
    private String birthday;
    //性别
    private String sex;
    //个性签名
    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimgpath() {
        return userimgpath;
    }

    public void setUserimgpath(String userimgpath) {
        this.userimgpath = userimgpath;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userimgpath='" + userimgpath + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
