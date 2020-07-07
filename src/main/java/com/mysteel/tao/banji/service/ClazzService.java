package com.mysteel.tao.banji.service;

import com.mysteel.tao.banji.entity.Clazz;
import com.mysteel.tao.laoshi.eneity.Teacher;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public interface ClazzService {

    /**
     * @param clazz
     * @param teacherId
     * @return
     */
    Clazz save(Clazz clazz,String teacherId);

    /**
     * @param id
     * @return
     */
    Clazz findClazz(String id);

    /**
     * @param id
     */
    void delete(String id);

}
