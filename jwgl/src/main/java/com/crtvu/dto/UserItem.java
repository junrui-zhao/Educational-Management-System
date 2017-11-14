package com.crtvu.dto;

/**
 * Created by Phoenix on 2017/4/7.
 */
public class UserItem {

    // 用户Id
    private String userId;

    // 用户密码
    private String userPassword;

    // 用户类型
    private String userType;

    public UserItem() {
    }

    public UserItem(String userId, String userPassword, String userType) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
