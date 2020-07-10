package com.mysteel.tao.analysis.dto;

import com.mysteel.tao.test.dto.TestDTO;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public class AnalysisScoreDTO {

    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    private Double middleScore;
    private List<PieAnalysisScoreDTO> pies;
    private LineAnalysisScoreDTO line;


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

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getMiddleScore() {
        return middleScore;
    }

    public void setMiddleScore(Double middleScore) {
        this.middleScore = middleScore;
    }

    public List<PieAnalysisScoreDTO> getPies() {
        return pies;
    }

    public void setPies(List<PieAnalysisScoreDTO> pies) {
        this.pies = pies;
    }

    public LineAnalysisScoreDTO getLine() {
        return line;
    }

    public void setLine(LineAnalysisScoreDTO line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "AnalysisStudentDTO{" +
                "maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", avgScore=" + avgScore +
                ", middleScore=" + middleScore +
                ", pies=" + pies +
                ", line=" + line +
                '}';
    }

    public void budilerTestDTO(TestDTO testDTO) {
        this.maxScore = testDTO.getMaxScore();
        this.minScore = testDTO.getMinScore();
        this.avgScore = testDTO.getAvgScore();
    }

}


