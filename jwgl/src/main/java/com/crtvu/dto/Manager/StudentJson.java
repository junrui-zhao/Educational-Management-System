package com.crtvu.dto.Manager;

/**
 * Created by lcf12 on 2017/3/25.
 */
public class StudentJson {
    private int id;//新学生的id
    private String name;//新学生的名字
    private String className;//新学生的班级
    private String major;//新学生的专业
    private boolean success;//是否修改成功
    private String error;//失败原因
    private String password;//学生密码
    @Override
    public String toString() {
        return "StudentJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", success=" + success +
                ", error='" + error + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public StudentJson(int id, String name, String className, String major, boolean success, String error, String password) {

        this.id = id;
        this.name = name;
        this.className = className;
        this.major = major;
        this.success = success;
        this.error = error;
        this.password = password;
    }



    public StudentJson(int id, String name, String className, String major) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.major = major;
    }

    public StudentJson(int id, String name, String className, String major, String password) {

        this.id = id;
        this.name = name;
        this.className = className;
        this.major = major;
        this.password = password;
    }

    public StudentJson(int id) {

        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentJson() {

    }
}
