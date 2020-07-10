package com.mysteel.tao.analysis.service;

import com.mysteel.tao.analysis.dto.AnalysisScoreDTO;
import com.mysteel.tao.analysis.dto.AnalysisTestScoreDTO;

/**
 * @author GLNC-taowenchen
 */
public interface AnalysisService {
    AnalysisScoreDTO analysisStudent(String studentId);

    AnalysisScoreDTO analysisTest(String testId);

    AnalysisTestScoreDTO analysisAllTest(String clazzId);

}
