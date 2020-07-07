package com.mysteel.tao.laoshi.controller;

import com.mysteel.tao.laoshi.dto.TeacherDTO;
import com.mysteel.tao.laoshi.eneity.Teacher;
import com.mysteel.tao.laoshi.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @PostMapping("/")
    public Teacher saveTeacher(@RequestBody Teacher t) {
        return teacherService.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        teacherService.delete(id);
    }

    @GetMapping("/{id}")
    public TeacherDTO findById(@PathVariable String id) {
        return teacherService.findUser(id);
    }

    @GetMapping("/")
    public List<Teacher> findList() {
        return teacherService.findList(0, 0);
    }


    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
