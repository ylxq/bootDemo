package com.mysteel.tao.analysis.service;


import com.mysteel.tao.analysis.dto.AnalysisScoreDTO;
import com.mysteel.tao.analysis.dto.AnalysisTestScoreDTO;
import com.mysteel.tao.analysis.dto.LineAnalysisScoreDTO;
import com.mysteel.tao.analysis.dto.PieAnalysisScoreDTO;
import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.chengji.dao.ScoreRepository;
import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.test.dao.TestRepository;
import com.mysteel.tao.test.dto.TestDTO;
import com.mysteel.tao.utils.ScoreCountUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author GLNC-taowenchen
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Override
    public AnalysisScoreDTO analysisStudent(String studentId) {
        List<Score> scores = scoreRepository.findScoreByStudentId(studentId);
        if (scores == null || scores.isEmpty()) {
            return null;
        }
        return getAnalysisScoreDTO(scores);
    }


    @Override
    public AnalysisScoreDTO analysisTest(String testId) {
        List<Score> scores = scoreRepository.findScoreByTestId(testId);
        if (scores == null || scores.isEmpty()) {
            return null;
        }
        return getAnalysisScoreDTO(scores);
    }

    @Override
    public AnalysisTestScoreDTO analysisAllTest(String clazzId) {
        List<AllTestAnalysisScoreDTO> list = new ArrayList<>();
        clazzRepository.findById(clazzId).ifPresent(clazz -> {
            Set<String> set = clazz.getTest();
            Optional.ofNullable(set).ifPresent(sets -> sets.forEach(value -> {
                AllTestAnalysisScoreDTO all = new AllTestAnalysisScoreDTO();
                AnalysisScoreDTO analysisScoreDTO = analysisTest(value);
                if (analysisScoreDTO != null) {
                    all.setAnalysisScoreDTO(analysisScoreDTO);
                    all.setTestName(testRepository.findById(value).get().getName());
                    list.add(all);
                }
            }));
        });


        AnalysisTestScoreDTO analysisTestScoreDTO = new AnalysisTestScoreDTO();

        list.forEach(testAnalysis -> {
            AnalysisScoreDTO analysisScoreDTO = testAnalysis.getAnalysisScoreDTO();
            setAnalysisData(analysisTestScoreDTO.getMax(), testAnalysis, analysisScoreDTO.getMaxScore());
            setAnalysisData(analysisTestScoreDTO.getMin(), testAnalysis, analysisScoreDTO.getMinScore());
            setAnalysisData(analysisTestScoreDTO.getAvg(), testAnalysis, analysisScoreDTO.getAvgScore());
            analysisScoreDTO.getPies()
                    .forEach(analysisScorePie -> setAnalysisData(analysisTestScoreDTO.getObjectByKey(analysisScorePie.getName()), testAnalysis, Double.valueOf(analysisScorePie.getValue())));
        });

        return analysisTestScoreDTO;
    }

    private void setAnalysisData(LineAnalysisScoreDTO max, AllTestAnalysisScoreDTO testAnalysis, Double value) {
        if (max == null) {
            return;
        }
        max.getTestName().add(testAnalysis.getTestName());
        max.getTestScore().add(value);
    }


    private AnalysisScoreDTO getAnalysisScoreDTO(List<Score> scores) {
        AnalysisScoreDTO analysisStudentDTO = new AnalysisScoreDTO();
        //均分
        TestDTO testDTO = ScoreCountUtils.getTestDTO(scores);
        analysisStudentDTO.budilerTestDTO(testDTO);

        //饼图分布
        List<PieAnalysisScoreDTO> list = ScoreCountUtils.getPieAnalysisScoreDTOS(scores);
        analysisStudentDTO.setPies(list);

        //折线序列
        LineAnalysisScoreDTO lineAnalysisStudentDTO = ScoreCountUtils.getLineAnalysisScoreDTO(scores);
        analysisStudentDTO.setLine(lineAnalysisStudentDTO);
        return analysisStudentDTO;
    }

    private final ScoreRepository scoreRepository;
    private final ClazzRepository clazzRepository;
    private final TestRepository testRepository;

    public AnalysisServiceImpl(ScoreRepository scoreRepository, ClazzRepository clazzRepository, TestRepository testRepository) {
        this.scoreRepository = scoreRepository;
        this.clazzRepository = clazzRepository;
        this.testRepository = testRepository;
    }
}

class AllTestAnalysisScoreDTO {
    private AnalysisScoreDTO analysisScoreDTO;
    private String testName;

    public AnalysisScoreDTO getAnalysisScoreDTO() {
        return analysisScoreDTO;
    }

    public void setAnalysisScoreDTO(AnalysisScoreDTO analysisScoreDTO) {
        this.analysisScoreDTO = analysisScoreDTO;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
