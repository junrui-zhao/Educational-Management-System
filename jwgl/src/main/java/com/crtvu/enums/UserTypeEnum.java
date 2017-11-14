package com.crtvu.enums;

/**
 * Created by Phoenix on 2017/4/7.
 */
public enum UserTypeEnum {

    STUDENT(1), TEACHER(2), ADMIN(3);

    private int index;

    UserTypeEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
