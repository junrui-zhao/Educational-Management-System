package com.crtvu.dao;

import java.util.List;

/**
 * Created by Jixw on 2017/3/30.
 */
public interface TeachCourseDAO {

    /**
     * 根据教师id查询所有的开设ID
     * @param teacherId
     * @return
     */
    List<Integer> getAllOpenId(int teacherId);

    /**
     * 根据OpenId查询所属老师
     * @param openId
     * @return
     */
    int getTeacherId(int openId);

    /**
     * 根据teacherId查询openId
     * @param teacherId
     * @return openId
     */
    int queryOpenIdByTeacherId(int teacherId);

}
