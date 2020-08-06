package com.mysteel.tao.test.controller;

import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.test.dto.TestDTO;
import com.mysteel.tao.test.dto.TestDetailDTO;
import com.mysteel.tao.test.eneity.Test;
import com.mysteel.tao.test.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/")
    public Test saveTest(@RequestBody Test t,String clazzId) {
        return testService.save(t,clazzId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        testService.delete(id);
    }

    @GetMapping("/{id}")
    public TestDetailDTO findById(@PathVariable String id) {
        return testService.findTest(id);
    }

    @GetMapping("/clazzId/{clazzId}")
    public List<TestDTO> findListByClazzId(@PathVariable String clazzId) {
        return testService.findListByClazzId(clazzId);
    }

    @GetMapping("/student/{studentId}")
    public List<Score> findListByStudentId(@PathVariable String studentId) {
        return testService.findListByStudentId(studentId);
    }


    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
}
