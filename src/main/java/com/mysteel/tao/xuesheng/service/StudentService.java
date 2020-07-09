package com.mysteel.tao.xuesheng.service;

import com.mysteel.tao.xuesheng.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author GLNC-taowenchen
 */
public interface StudentService {

    /**
     * @param student
     * @return
     */
    Student save(Student student,String clazz);

    /**
     * @param id
     * @return
     */
    Student findStudent(String id);

    /**
     * @param id
     */
    void delete(String id);


    /**
     *
     * @return
     */
    List<Student> findByClazzId(String clazzId);

    void batchSaveByExcel(MultipartFile file,String clazzId) throws IOException;
}
