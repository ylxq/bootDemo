package com.mysteel.tao.laoshi.dao;


import com.mysteel.tao.laoshi.eneity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GLNC-taowenchen
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {

    Teacher findTeacherByName(String name);

}
