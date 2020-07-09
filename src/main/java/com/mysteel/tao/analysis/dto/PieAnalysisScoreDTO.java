package com.mysteel.tao.analysis.dto;

/**
 * @author GLNC-taowenchen
 */
public class PieAnalysisScoreDTO {
    private String key;
    private Integer count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PieAnalysisStudentDTO{" +
                "key='" + key + '\'' +
                ", count=" + count +
                '}';
    }
}
