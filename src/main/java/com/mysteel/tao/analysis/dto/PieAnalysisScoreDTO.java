package com.mysteel.tao.analysis.dto;

/**
 * @author GLNC-taowenchen
 */
public class PieAnalysisScoreDTO {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PieAnalysisStudentDTO{" +
                "key='" + name + '\'' +
                ", count=" + value +
                '}';
    }
}
