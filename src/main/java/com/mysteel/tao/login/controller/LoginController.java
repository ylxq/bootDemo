package com.mysteel.tao.login.controller;

import com.mysteel.tao.common.ResponseResult;
import com.mysteel.tao.laoshi.eneity.Teacher;
import com.mysteel.tao.laoshi.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {


    private final TeacherService teacherService;

    public LoginController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<Teacher> login(String userName, String password) {

        Teacher teacher = teacherService.findByName(userName);
        if (teacher == null) {
            Teacher t = new Teacher();
            t.setName(userName);
            t.setPassword(password);
            teacher = teacherService.save(t);
        }
        return new ResponseResult<>(200, teacher);
    }

}
