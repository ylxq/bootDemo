package com.mysteel.tao.chengji.dao;


import com.mysteel.tao.chengji.eneity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@Repository
public interface ScoreRepository extends CrudRepository<Score, String> {

    /**
     * @param testId
     * @return
     */
    List<Score> findScoreByTestId(String testId);


    /**
     * @param studentId
     * @return
     */
    List<Score> findScoreByStudentId(String studentId);



    Score findScoreByTestIdAndStudentId(String testId, String studentId);

    void deleteScoreByTestId(String testId);

    void deleteScoreByStudentId(String studentId);
}
