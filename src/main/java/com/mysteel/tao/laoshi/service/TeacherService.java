package com.mysteel.tao.laoshi.service;

import com.mysteel.tao.laoshi.dto.TeacherDTO;
import com.mysteel.tao.laoshi.eneity.Teacher;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public interface TeacherService {

    /**
     * @param teacher
     * @return
     */
    Teacher save(Teacher teacher);

    /**
     * @param id
     * @return
     */
    TeacherDTO findUser(String id);

    Teacher findByName(String name);

    /**
     * @param id
     */
    void delete(String id);



    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Teacher> findList(Integer page,Integer pageSize);
}
