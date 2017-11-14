package com.crtvu.dto.student;

/**
 * Created by Jixw on 2017/4/4.
 */
public class TeacherNoPwd {

    private int id;              //教师工号

    private String name;            //教师姓名

    private String title;           //教师职称

    public TeacherNoPwd(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TeacherNoPwd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
