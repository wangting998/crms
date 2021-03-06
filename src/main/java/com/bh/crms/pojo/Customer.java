package com.bh.crms.pojo;

import java.util.Date;

/**
 * 该类与数据库中的表是相对应的
 * @Author WWT
 * @Date 2021/1/27
 */
public class Customer {
    /**
     * 对应数据库表
     */
    private String cid; //客户id
    private String cname; //客户姓名
    private String gender; //客户性别
    private String birthday;  //客户生日
    private String cellphone; //客户手机
    private String email; //客户邮箱
    private String description; //客户描述
    private String ebable; //字段

    /**
     * 无餐构造  当生成有参构造时，必须生成无参构造
     */
    public Customer() {
    }

    /**
     * 生成构造参数
     * @param cid
     * @param cname
     * @param gender
     * @param birthday
     * @param cellphone
     * @param email
     * @param description
     * @param ebable
     */
    public Customer(String cid, String cname, String gender, String birthday, String cellphone, String email, String description, String ebable) {
        this.cid = cid;
        this.cname = cname;
        this.gender = gender;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.email = email;
        this.description = description;
        this.ebable = ebable;
    }


    public Customer(String cid, String cname, String gender, String birthday, String cellphone, String email, String description) {
        this.cid = cid;
        this.cname = cname;
        this.gender = gender;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.email = email;
        this.description = description;
    }

    public Customer(String cname, String gender, String cellphone, String email) {
        this.cname = cname;
        this.gender = gender;
        this.cellphone = cellphone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", ebable='" + ebable + '\'' +
                '}';
    }

    /**
     * 生成get、set方法
     * @return
     */
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEbable() {
        return ebable;
    }

    public void setEbable(String ebable) {
        this.ebable = ebable;
    }
}
