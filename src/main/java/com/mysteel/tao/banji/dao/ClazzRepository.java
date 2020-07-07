package com.mysteel.tao.banji.dao;


import com.mysteel.tao.banji.entity.Clazz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GLNC-taowenchen
 */
@Repository
public interface ClazzRepository extends CrudRepository<Clazz, String> {

}
