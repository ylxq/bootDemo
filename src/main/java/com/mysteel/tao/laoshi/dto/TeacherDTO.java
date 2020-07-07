package com.mysteel.tao.laoshi.dto;

import com.mysteel.tao.banji.entity.Clazz;

import java.util.List;

public class TeacherDTO {
    private String id;
    private String name;
    private List<Clazz> clazzList;

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

    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", clazzList=" + clazzList +
                '}';
    }
}
