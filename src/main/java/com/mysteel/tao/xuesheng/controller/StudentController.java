package com.mysteel.tao.xuesheng.controller;

import com.alibaba.excel.EasyExcel;
import com.mysteel.tao.xuesheng.entity.Student;
import com.mysteel.tao.xuesheng.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @PostMapping("/")
    public Student saveStudent(@RequestBody Student t) {
        return studentService.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(id);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable String id) {
        return studentService.findStudent(id);
    }

    @GetMapping("/")
    public List<Student> findByClazzId(String clazzId) {
        return studentService.findByClazzId(clazzId);
    }



    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        studentService.batchSaveByExcel(file);
    }

    @GetMapping("/download")
    public void download(String clazzId, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("导入模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class)
                .sheet("花名册")
                .doWrite(studentService.findByClazzId(clazzId));
    }


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}
