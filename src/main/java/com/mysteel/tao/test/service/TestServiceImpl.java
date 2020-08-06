package com.mysteel.tao.test.service;

import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.chengji.dao.ScoreRepository;
import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.test.dao.TestRepository;
import com.mysteel.tao.test.dto.TestDTO;
import com.mysteel.tao.test.dto.TestDetailDTO;
import com.mysteel.tao.test.eneity.Test;
import com.mysteel.tao.utils.ScoreCountUtils;
import com.mysteel.tao.xuesheng.dao.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author GLNC-taowenchen
 */
@Service
public class TestServiceImpl implements TestService {


    @Override
    public Test save(Test test, String clazzId) {
        Test saveTest = repository.save(test);
        clazzRepository.findById(clazzId).ifPresent(clazz -> {
            Set<String> tests = clazz.getTest();
            if (tests == null) {
                tests = new HashSet<>();
            }
            tests.add(saveTest.getId());
            clazz.setTest(tests);
            clazzRepository.save(clazz);
        });
        return saveTest;
    }


    @Override
    public TestDetailDTO findTest(String id) {
        TestDetailDTO testDetailDTO = new TestDetailDTO();
        repository.findById(id).ifPresent(test -> {
            testDetailDTO.setId(test.getId());
            testDetailDTO.setName(test.getName());
            List<Score> scores = testDetailDTO.getScores() == null ? new ArrayList<>() : testDetailDTO.getScores();
            Optional.ofNullable(scoreRepository.findScoreByTestId(test.getId()))
                    .ifPresent(list -> {
                        list.forEach(score -> {
                            Optional.ofNullable(score).ifPresent(scores::add);
                        });
                    });
            testDetailDTO.setScores(scores);
        });

        return testDetailDTO;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
        scoreRepository.deleteScoreByTestId(id);
    }

    @Override
    public List<TestDTO> findListByClazzId(String clazzId) {
        List<TestDTO> tests = new ArrayList<>();
        clazzRepository.findById(clazzId)
                .flatMap(clazz -> Optional.ofNullable(clazz.getTest()))
                .ifPresent(set -> set.forEach(testId -> {
                    Test test = repository.findById(testId).get();
                    List<Score> scores = scoreRepository.findScoreByTestId(testId);
                    TestDTO testDTO = ScoreCountUtils.getTestDTO(scores);
                    testDTO.setId(testId);
                    testDTO.setName(test.getName());
                    tests.add(testDTO);
                }));
        return tests;
    }


    @Override
    public List<Score> findListByStudentId(String studentId) {
        List<Score> scores = scoreRepository.findScoreByStudentId(studentId);
        Optional.ofNullable(scores).ifPresent(list -> {
            list.forEach(score -> {
                Object[] scoreRanks = scoreRepository.findScoreByTestId(score.getTestId()).stream().map(Score::getScore).sorted().toArray();
                int rank = Arrays.binarySearch(scoreRanks, score.getScore());
                score.setRank(scoreRanks.length - rank);
            });
        });
        return scores;
    }

    private final TestRepository repository;
    private final ClazzRepository clazzRepository;
    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;

    public TestServiceImpl(TestRepository repository, ClazzRepository clazzRepository, ScoreRepository scoreRepository, StudentRepository studentRepository) {
        this.repository = repository;
        this.clazzRepository = clazzRepository;
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
    }
}
