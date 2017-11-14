package com.crtvu.dao;

import com.crtvu.entity.Arrangement;

import java.util.LinkedList;

/**
 * Created by Phoenix on 2017/5/7.
 */
public interface ArrangementDAO {

    /**
     * 获取全部安排信息
     * @return
     */
    LinkedList<Arrangement> queryAllArrangement();

    /**
     * 通过开设id获取安排信息
     * @param openId
     * @return
     */
    LinkedList<Arrangement> queryArrangementByOpenId(int openId);

    /**
     * 通过教师id获取安排信息
     * @param teacherId
     * @return
     */
    LinkedList<Arrangement> queryArrangementByTeacherId(int teacherId);

}
