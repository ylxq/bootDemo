package com.mysteel.tao.laoshi.service;

import com.google.common.collect.Lists;
import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.banji.service.ClazzService;
import com.mysteel.tao.laoshi.dao.TeacherRepository;
import com.mysteel.tao.laoshi.dto.TeacherDTO;
import com.mysteel.tao.laoshi.eneity.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author GLNC-taowenchen
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public Teacher save(Teacher teacher) {
        if (StringUtils.isNotEmpty(teacher.getId())) {
            repository
                    .findById(teacher.getId())
                    .ifPresent(t -> teacher.setClazz(t.getClazz())
                    );

        }
        return repository.save(teacher);
    }


    @Override
    public TeacherDTO findUser(String id) {
        TeacherDTO teacher = new TeacherDTO();
        repository.findById(id).ifPresent(
                t -> {
                    teacher.setId(t.getId());
                    teacher.setName(t.getName());
                    teacher.setClazzList(new ArrayList<>());
                    Optional.ofNullable(t.getClazz())
                            .ifPresent(list -> {
                                list.forEach(val -> {
                                    Optional.ofNullable(clazzService.findClazz(val))
                                            .ifPresent(clazz -> teacher.getClazzList().add(clazz));
                                });
                            });
                }
        );

        return teacher;
    }

    @Override
    public Teacher findByName(String name) {
        return repository.findTeacherByName(name);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }


    @Override
    public List<Teacher> findList(Integer page, Integer pageSize) {
        return Lists.newArrayList(repository.findAll());
    }


    private final TeacherRepository repository;
    private final ClazzService clazzService;


    public TeacherServiceImpl(TeacherRepository repository, ClazzService clazzService) {
        this.repository = repository;
        this.clazzService = clazzService;
    }
}
