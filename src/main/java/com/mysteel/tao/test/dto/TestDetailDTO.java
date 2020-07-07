package com.mysteel.tao.test.dto;


import com.mysteel.tao.chengji.eneity.Score;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public class TestDetailDTO {
    private String id;
    private String name;
    private List<Score> scores;


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

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "TestDetailDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
