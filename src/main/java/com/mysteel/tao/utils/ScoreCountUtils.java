package com.mysteel.tao.utils;

import com.mysteel.tao.analysis.dto.LineAnalysisScoreDTO;
import com.mysteel.tao.analysis.dto.PieAnalysisScoreDTO;
import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.test.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GLNC-taowenchen
 */
public class ScoreCountUtils {

    /**
     * 均值计算
     *
     * @param scores
     * @return
     */
    public static TestDTO getTestDTO(List<Score> scores) {
        List<Double> scoreList = scores.stream().map(Score::getScore).collect(Collectors.toList());
        Double maxScore = scoreList.stream().max(Double::compareTo).orElse(0D);
        Double minScore = scoreList.stream().min(Double::compareTo).orElse(0D);
        Double avgScore = scores.stream().mapToDouble(Score::getScore).average().orElse(0D);
        Double sum = scores.stream().mapToDouble(Score::getScore).sum();


        TestDTO testDTO = new TestDTO();
        testDTO.setAvgScore(avgScore);
        testDTO.setMaxScore(maxScore);
        testDTO.setMinScore(minScore);
        testDTO.setSumScore(sum);
        return testDTO;
    }

    /**
     * 饼图分布
     *
     * @param scores
     * @return
     */
    public static List<PieAnalysisScoreDTO> getPieAnalysisScoreDTOS(List<Score> scores) {
        List<PieAnalysisScoreDTO> list = new ArrayList<>();
        Map<ScoreCountEnum, List<Score>> map = scores.stream()
                .collect(Collectors.groupingBy(
                        score -> ScoreCountEnum.getEnumByScore(score.getScore())));
        for (Map.Entry<ScoreCountEnum, List<Score>> entry : map.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                PieAnalysisScoreDTO pie = new PieAnalysisScoreDTO();
                pie.setKey(entry.getKey().getName());
                pie.setCount(entry.getValue().size());
                list.add(pie);
            }
        }
        return list;
    }

    /**
     * 折线图
     *
     * @param scores
     * @return
     */
    public static LineAnalysisScoreDTO getLineAnalysisScoreDTO(List<Score> scores) {
        LineAnalysisScoreDTO lineAnalysisStudentDTO = new LineAnalysisScoreDTO();
        List<String> testName = new ArrayList<>();
        List<Double> datas = new ArrayList<>();
        scores.forEach(score -> {
            testName.add(score.getTestName());
            datas.add(score.getScore());
        });
        lineAnalysisStudentDTO.setTestName(testName);
        lineAnalysisStudentDTO.setTestScore(datas);
        return lineAnalysisStudentDTO;
    }

}
