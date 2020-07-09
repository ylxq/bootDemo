package com.mysteel.tao.analysis.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public class LineAnalysisScoreDTO {
    private List<String> testName = new ArrayList<>();
    private List<Double> testScore = new ArrayList<>();

    public List<String> getTestName() {
        return testName;
    }

    public void setTestName(List<String> testName) {
        this.testName = testName;
    }

    public List<Double> getTestScore() {
        return testScore;
    }

    public void setTestScore(List<Double> testScore) {
        this.testScore = testScore;
    }

    @Override
    public String toString() {
        return "LineAnalysisStudentDTO{" +
                "testName=" + testName +
                ", testScore=" + testScore +
                '}';
    }
}
