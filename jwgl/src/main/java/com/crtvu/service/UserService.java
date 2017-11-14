package com.crtvu.service;

import com.crtvu.entity.TermYearEntity;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/4/11.
 */
public interface UserService {

    // 登陆
    public int login(String userName, String userPassword, String userType);

    public TermYearEntity getCurrentTermAndYear();

    public TermYearEntity getRecentTermAndYear();

    LinkedList<TermYearEntity> getAllTermAndYear();
}
