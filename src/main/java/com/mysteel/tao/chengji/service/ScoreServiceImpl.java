package com.mysteel.tao.chengji.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.banji.entity.Clazz;
import com.mysteel.tao.chengji.dao.ScoreRepository;
import com.mysteel.tao.chengji.dto.ExcelScoreDownloadData;
import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.laoshi.eneity.Teacher;
import com.mysteel.tao.test.dao.TestRepository;
import com.mysteel.tao.xuesheng.dao.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GLNC-taowenchen
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Override
    public Score save(Score score) {
        testRepository.findById(score.getTestId()).ifPresent(test -> {
            score.setTestName(test.getName());
        });

        studentRepository.findById(score.getStudentId()).ifPresent(student -> {
            score.setStudentName(student.getName());
        });
        return repository.save(score);
    }

    @Override
    public void batchSaveByExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ExcelScoreDownloadData.class, new AnalysisEventListener<ExcelScoreDownloadData>() {
            @Override
            public void invoke(ExcelScoreDownloadData data, AnalysisContext analysisContext) {
                Score score = new Score();
                score.setTestId(data.getTestId());
                score.setStudentId(data.getStudentId());
                score.setStudentName(data.getStudentName());
                score.setScore(data.getScore());
                save(score);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();

    }

    @Override
    public List findByTestIdAndClazzId(String testId, String clazzId) {
        List<ExcelScoreDownloadData> list = new ArrayList<>();
        clazzRepository.findById(clazzId).ifPresent(clazz -> {
            clazz.getStudents().forEach(val -> studentRepository.findById(val)
                    .ifPresent(student -> {
                        ExcelScoreDownloadData excelScoreDownloadData = new ExcelScoreDownloadData();
                        excelScoreDownloadData.setTestId(testId);
                        excelScoreDownloadData.setClazzId(clazzId);
                        excelScoreDownloadData.setStudentId(val);
                        excelScoreDownloadData.setStudentName(student.getName());
                        excelScoreDownloadData.setStudentNo(student.getXueHao());
                        list.add(excelScoreDownloadData);
                    }));
        });

        return list;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }


    private final ScoreRepository repository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final ClazzRepository clazzRepository;

    public ScoreServiceImpl(ScoreRepository repository, TestRepository testRepository, StudentRepository studentRepository, ClazzRepository clazzRepository) {
        this.repository = repository;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
        this.clazzRepository = clazzRepository;
    }
}
