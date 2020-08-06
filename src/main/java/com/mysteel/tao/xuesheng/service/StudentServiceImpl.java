package com.mysteel.tao.xuesheng.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mysteel.tao.banji.dao.ClazzRepository;
import com.mysteel.tao.chengji.dao.ScoreRepository;
import com.mysteel.tao.xuesheng.dao.StudentRepository;
import com.mysteel.tao.xuesheng.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author GLNC-taowenchen
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Student save(Student student, String clazzId) {
        student.setSex(Integer.valueOf(student.getCardCode().substring(16, 17)) % 2 == 0 ? 1 : 0);
        Student newStudent = repository.save(student);
        clazzRepository.findById(clazzId).ifPresent(clazz -> {
            Set<String> students = clazz.getStudents();
            if (students == null) {
                students = new HashSet<>();
            }
            students.add(newStudent.getId());
            clazz.setStudents(students);
            clazzRepository.save(clazz);
        });
        return newStudent;
    }


    @Override
    public Student findStudent(String id) {
        return repository.findById(id).orElse(new Student());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
        scoreRepository.deleteScoreByStudentId(id);
    }

    @Override
    public List<Student> findByClazzId(String clazzId) {
        List<Student> students = new ArrayList<>();
        clazzRepository.findById(clazzId)
                .flatMap(clazz -> Optional.ofNullable(clazz.getStudents()))
                .ifPresent(list -> list.forEach(studentId -> {
                    repository.findById(studentId).ifPresent(students::add);
                }));
        return students;
    }


    @Override
    public void batchSaveByExcel(MultipartFile file, String clazzId) throws IOException {
        EasyExcel.read(file.getInputStream(), Student.class, new AnalysisEventListener<Student>() {
            @Override
            public void invoke(Student data, AnalysisContext analysisContext) {
                save(data, clazzId);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    private final StudentRepository repository;
    private final ClazzRepository clazzRepository;
    private final ScoreRepository scoreRepository;

    public StudentServiceImpl(StudentRepository repository, ClazzRepository clazzRepository, ScoreRepository scoreRepository) {
        this.repository = repository;
        this.clazzRepository = clazzRepository;
        this.scoreRepository = scoreRepository;
    }
}
