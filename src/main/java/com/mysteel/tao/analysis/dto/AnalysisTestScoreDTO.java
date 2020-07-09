package com.mysteel.tao.analysis.dto;

import com.mysteel.tao.utils.ScoreCountEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author GLNC-taowenchen
 */
public class AnalysisTestScoreDTO {

    private LineAnalysisScoreDTO max = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO min = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO avg = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO ten = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO ninety = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO eighty = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO seventy = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO sixty = new LineAnalysisScoreDTO();
    private LineAnalysisScoreDTO fifty = new LineAnalysisScoreDTO();

    public LineAnalysisScoreDTO getMax() {
        return max;
    }

    public void setMax(LineAnalysisScoreDTO max) {
        this.max = max;
    }

    public LineAnalysisScoreDTO getMin() {
        return min;
    }

    public void setMin(LineAnalysisScoreDTO min) {
        this.min = min;
    }

    public LineAnalysisScoreDTO getAvg() {
        return avg;
    }

    public void setAvg(LineAnalysisScoreDTO avg) {
        this.avg = avg;
    }

    public LineAnalysisScoreDTO getTen() {
        return ten;
    }

    public void setTen(LineAnalysisScoreDTO ten) {
        this.ten = ten;
    }

    public LineAnalysisScoreDTO getNinety() {
        return ninety;
    }

    public void setNinety(LineAnalysisScoreDTO ninety) {
        this.ninety = ninety;
    }

    public LineAnalysisScoreDTO getEighty() {
        return eighty;
    }

    public void setEighty(LineAnalysisScoreDTO eighty) {
        this.eighty = eighty;
    }

    public LineAnalysisScoreDTO getSeventy() {
        return seventy;
    }

    public void setSeventy(LineAnalysisScoreDTO seventy) {
        this.seventy = seventy;
    }

    public LineAnalysisScoreDTO getSixty() {
        return sixty;
    }

    public void setSixty(LineAnalysisScoreDTO sixty) {
        this.sixty = sixty;
    }

    public LineAnalysisScoreDTO getFifty() {
        return fifty;
    }

    public void setFifty(LineAnalysisScoreDTO fifty) {
        this.fifty = fifty;
    }


    public LineAnalysisScoreDTO getObjectByKey(String key) {
        ScoreCountEnum scoreCountEnum = ScoreCountEnum.getEnumByName(key);
        Method method;
        LineAnalysisScoreDTO result;
        try {
            method = this.getClass().getMethod(scoreCountEnum.getAttr());
            result = (LineAnalysisScoreDTO) method.invoke(this);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

}
