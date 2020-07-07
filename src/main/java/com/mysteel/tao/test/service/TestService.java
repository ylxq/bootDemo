package com.mysteel.tao.test.service;

import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.test.dto.TestDTO;
import com.mysteel.tao.test.dto.TestDetailDTO;
import com.mysteel.tao.test.eneity.Test;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public interface TestService {

    /**
     * @param test
     * @param clazzId
     * @return
     */
    Test save(Test test,String clazzId);

    /**
     * @param id
     * @return
     */
    TestDetailDTO findTest(String id);

    /**
     * @param id
     */
    void delete(String id);

    /**
     *
     * @return
     */
    List<TestDTO> findListByClazzId(String clazzId);    /**
     *
     * @return
     */
    List<Score> findListByStudentId(String studentId);
}
