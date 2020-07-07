package com.mysteel.tao.chengji.controller;

import com.alibaba.excel.EasyExcel;
import com.mysteel.tao.chengji.dto.ExcelScoreDownloadData;
import com.mysteel.tao.chengji.eneity.Score;
import com.mysteel.tao.chengji.service.ScoreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author GLNC-taowenchen
 */
@RestController
@RequestMapping("/score")
public class ScoreController {


    @PostMapping("/")
    public Score saveScore(@RequestBody Score t) {
        return scoreService.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        scoreService.delete(id);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        scoreService.batchSaveByExcel(file);
    }

    @GetMapping("/download")
    public void download(String testId, String clazzId, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelScoreDownloadData.class)
                .sheet("模板")
                .doWrite(scoreService.findByTestIdAndClazzId(testId, clazzId));
    }

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
}
