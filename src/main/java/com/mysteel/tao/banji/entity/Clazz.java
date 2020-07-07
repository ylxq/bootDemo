package com.mysteel.tao.banji.entity;

import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

/**
 * @author GLNC-taowenchen
 */
@RedisHash("clazz")
public class Clazz {
    private String id;
    private String name;
    private Set<String> students;
    private Set<String> test;



    public Set<String> getTest() {
        return test;
    }

    public void setTest(Set<String> test) {
        this.test = test;
    }

    public Set<String> getStudents() {
        return students;
    }

    public void setStudents(Set<String> students) {
        this.students = students;
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

    @Override
    public String toString() {
        return "Clazz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
