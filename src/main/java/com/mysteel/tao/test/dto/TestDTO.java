package com.mysteel.tao.test.dto;


/**
 * @author GLNC-taowenchen
 */
public class TestDTO {
    private String id;
    private String name;
    private Double avgScore;
    private Double sumScore;
    private Double maxScore;
    private Double minScore;
    private Double middleScore;




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


    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getMiddleScore() {
        return middleScore;
    }

    public void setMiddleScore(Double middleScore) {
        this.middleScore = middleScore;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avgScore=" + avgScore +
                ", sumScore=" + sumScore +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", middleScore=" + middleScore +
                '}';
    }
}
