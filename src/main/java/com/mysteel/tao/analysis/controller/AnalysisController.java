package com.mysteel.tao.analysis.controller;

import com.mysteel.tao.analysis.dto.AnalysisScoreDTO;
import com.mysteel.tao.analysis.dto.AnalysisTestScoreDTO;
import com.mysteel.tao.analysis.service.AnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GLNC-taowenchen
 */
@RequestMapping("/analysis")
@RestController
public class AnalysisController {

    @GetMapping("/student/{id}")
    public AnalysisScoreDTO analysisStudentScore(@PathVariable String id) {
        return analysisService.analysisStudent(id);
    }

    @GetMapping("/test/{id}")
    public AnalysisScoreDTO analysisTestScore(@PathVariable String id) {
        return analysisService.analysisTest(id);
    }


    @GetMapping("/test")
    public AnalysisTestScoreDTO analysisAllTest(String clazzId) {
        return analysisService.analysisAllTest(clazzId);
    }


    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }


}
