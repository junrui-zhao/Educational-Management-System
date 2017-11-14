package com.crtvu.dto.Manager;

/**
 * Created by lcf12 on 2017/3/25.
 */
public class DeleteJson {

    private int page;//返回当前页数，暂时不用
    private int id;//学生学号

    @Override
    public String toString() {
        return "deletejson{" +
                "page=" + page +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeleteJson(int page, int id) {

        this.page = page;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public DeleteJson(int page) {

        this.page = page;
    }

    public DeleteJson() {

    }
}
