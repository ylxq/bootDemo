package com.mysteel.tao.test.dto;


/**
 * @author GLNC-taowenchen
 */
public class StudentTestDTO {
    private String id;
    private String name;
    private Double typeName;
    private Double score;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getTypeName() {
        return typeName;
    }

    public void setTypeName(Double typeName) {
        this.typeName = typeName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentTestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", typeName=" + typeName +
                ", score=" + score +
                '}';
    }
}
