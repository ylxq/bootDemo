package com.mysteel.tao.test.dao;


import com.mysteel.tao.test.eneity.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author GLNC-taowenchen
 */
@Repository
public interface TestRepository extends CrudRepository<Test, String> {

}
