package com.mysteel.tao.xuesheng.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@RedisHash("student")
public class Student {
    @ExcelProperty("学生唯一标志")
    private String id;
    @ExcelProperty("学号")
    private int xueHao;
    @ExcelProperty("名字")
    private String name;
    @ExcelIgnore
    private int sex;
    @ExcelProperty("身份证")
    private String cardCode;
    @ExcelProperty("地址")
    private String address;


    public int getXueHao() {
        return xueHao;
    }

    public void setXueHao(int xueHao) {
        this.xueHao = xueHao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", cardCode='" + cardCode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
