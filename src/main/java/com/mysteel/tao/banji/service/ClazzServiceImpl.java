package com.mysteel.tao.banji.service;

import com.google.common.collect.Lists;
import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.banji.entity.Clazz;
import com.mysteel.tao.laoshi.dao.TeacherRepository;
import com.mysteel.tao.laoshi.eneity.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author GLNC-taowenchen
 */
@Service
public class ClazzServiceImpl implements ClazzService {
    @Override
    public Clazz save(Clazz clazz, String teacherId) {
        if (StringUtils.isNotEmpty(clazz.getId())) {
            repository
                    .findById(clazz.getId())
                    .ifPresent(t -> {
                                teacherRepository.findById(teacherId).ifPresent(
                                        teacher -> {
                                            if (teacher.getClazz() == null) {
                                                teacher.setClazz(new HashSet<>());
                                            }
                                            teacher.getClazz().add(teacherId);
                                            teacherRepository.save(teacher);
                                        }
                                );
                                clazz.setStudents(t.getStudents());
                                clazz.setTest(t.getTest());
                            }
                    );
        }
        return repository.save(clazz);
    }


    @Override
    public Clazz findClazz(String id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }


    private final ClazzRepository repository;
    private final TeacherRepository teacherRepository;

    public ClazzServiceImpl(ClazzRepository repository, TeacherRepository teacherRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }
}
