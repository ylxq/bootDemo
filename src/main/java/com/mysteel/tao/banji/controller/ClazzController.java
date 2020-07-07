package com.mysteel.tao.banji.controller;

import com.mysteel.tao.banji.entity.Clazz;
import com.mysteel.tao.banji.service.ClazzService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GLNC-taowenchen
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {


    @PostMapping("/")
    public Clazz saveClazz(@RequestBody Clazz t,String teacherId) {
        return clazzService.save(t,teacherId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        clazzService.delete(id);
    }

    @GetMapping("/{id}")
    public Clazz findById(@PathVariable String id) {
        return clazzService.findClazz(id);
    }




    private final ClazzService clazzService;

    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }
}
