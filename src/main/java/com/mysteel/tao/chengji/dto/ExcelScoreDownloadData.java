package com.mysteel.tao.chengji.dto;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author GLNC-taowenchen
 */
public class ExcelScoreDownloadData {
    @ExcelProperty("本次成绩标志")
    private String scoreId;
    @ExcelProperty("考试ID")
    private String testId;
    @ExcelProperty("班级ID")
    private String clazzId;
    @ExcelProperty("学生唯一标志")
    private String studentId;
    @ExcelProperty("学生学号")
    private int studentNo;
    @ExcelProperty("学生姓名")
    private String studentName;
    @ExcelProperty("分数")
    private Double score;


    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
