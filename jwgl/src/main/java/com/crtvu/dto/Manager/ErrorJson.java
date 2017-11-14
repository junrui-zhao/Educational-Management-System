package com.crtvu.dto.Manager;

import com.crtvu.service.StudentService;

/**
 * Created by lcf12 on 2017/4/15.
 */
public class ErrorJson {

    private int  id;
    private String errors;

    @Override
    public String toString() {
        return "ErrorJson{" +
                "id=" + id +
                ", error=" + errors +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public ErrorJson(int id, String errors) {

        this.id = id;
        this.errors = errors;
    }

    public ErrorJson() {

    }
}
