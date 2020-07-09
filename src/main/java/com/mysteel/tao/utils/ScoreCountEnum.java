package com.mysteel.tao.utils;

public enum ScoreCountEnum {

    /**
     * 1
     */
    TEN_TI(100.0D, 100.0D, "100", "getTen"),
    NINETY_TI(90.0D, 99.0D, "90-99", "getNinety"),
    EIGHTY_TI(80.0D, 89.0D, "80-89", "getEighty"),
    SEVENTY_TI(70.0D, 79.0D, "70-79", "getSeventy"),
    SIXTY_TI(60.0D, 69.0D, "60-69", "getSixty"),
    FIFTY_TI(0.0D, 59.0D, "0-59", "getFifty"),
    ;

    private Double min;
    private Double max;
    private String name;
    private String attr;

    ScoreCountEnum(Double min, Double max, String name, String attr) {
        this.min = min;
        this.max = max;
        this.name = name;
        this.attr = attr;
    }

    public String getName() {
        return name;
    }

    public String getAttr() {
        return attr;
    }

    public static ScoreCountEnum getEnumByScore(Double score) {
        for (ScoreCountEnum temp : ScoreCountEnum.values()) {
            if (score <= temp.max && score >= temp.min) {
                return temp;
            }
        }
        return FIFTY_TI;
    }

    public static ScoreCountEnum getEnumByName(String key) {
        for (ScoreCountEnum temp : ScoreCountEnum.values()) {
            if (temp.getName().equals(key)) {
                return temp;
            }
        }
        return FIFTY_TI;
    }


}
