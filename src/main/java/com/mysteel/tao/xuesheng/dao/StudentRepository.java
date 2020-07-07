package com.mysteel.tao.xuesheng.dao;


import com.mysteel.tao.xuesheng.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GLNC-taowenchen
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}
