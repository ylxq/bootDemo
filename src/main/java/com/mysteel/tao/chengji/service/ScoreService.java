package com.mysteel.tao.chengji.service;

import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.laoshi.eneity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public interface ScoreService {

    /**
     * @param score
     * @return
     */
    Score save(Score score);


    void batchSaveByExcel(MultipartFile file) throws IOException;

    /**
     * @param id
     */
    void delete(String id);


    List findByTestIdAndClazzId(String testId, String clazzId);
}
