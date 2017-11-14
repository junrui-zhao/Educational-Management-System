package com.crtvu.dto.Manager;

/**
 * Created by lcf12 on 2017/3/25.
 */
public class PasswordJson {
    private int id;//需要修改密码的学生学号
    private String newPassword;//新密码
    private boolean success;//是否成功
    private String error;//失败原因



    @Override
    public String toString() {
        return "PasswordJson{" +
                "id='" + id + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", sucess='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public PasswordJson(int id, String newPassword, boolean success, String error) {
        this.id = id;
        this.newPassword = newPassword;
        this.success = success;
        this.error = error;
    }

    public boolean getSucess() {

        return success;
    }

    public void setSucess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public PasswordJson() {
    }

    public PasswordJson(int id, String newPassword) {
        this.id = id;
        this.newPassword = newPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}