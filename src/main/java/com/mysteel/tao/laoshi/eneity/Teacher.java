package com.mysteel.tao.laoshi.eneity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
import java.util.Set;

@RedisHash(value = "teacher")
public class Teacher {

    @Id
    private String id;
    @Indexed
    private String name;
    private String password;
    private Set<String> clazz;


    public Set<String> getClazz() {
        return clazz;
    }

    public void setClazz(Set<String> clazz) {
        this.clazz = clazz;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
