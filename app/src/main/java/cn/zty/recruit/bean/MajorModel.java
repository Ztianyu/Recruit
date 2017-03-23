package cn.zty.recruit.bean;

import cn.zty.linkage.bean.BaseMenuBean;

/**
 * Created by zty on 2017/3/8.
 */

public class MajorModel extends BaseMenuBean {

    private String id;
    private String name;
    private String discipline;
    private String disciplineLabel;
    private String number;

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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDisciplineLabel() {
        return disciplineLabel;
    }

    public void setDisciplineLabel(String disciplineLabel) {
        this.disciplineLabel = disciplineLabel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
