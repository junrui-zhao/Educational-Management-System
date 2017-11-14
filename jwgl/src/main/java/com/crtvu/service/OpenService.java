package com.crtvu.service;

import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.TargetMajorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by danagi on 2017/4/10.
 */
public interface OpenService {
    enum Result{
        SUCCESS,
        NUM_FAIL,
        DATE_FAIL,
        KEY_FAIL,
        MAJOR_FAIL,
        ID_FAIL
    }

    /**
     * 开课
     * @param openEntity
     * @return
     */
    Result openCourse(OpenEntity openEntity, Set<String> majors);

    /**
     * 删除开设信息
     * @param openId
     * @return
     */
    Result removeOpen(int openId);

    /**
     * 修改开设信息
     * @param openEntity
     * @param majors
     * @return
     */
    Result modifyOpen(OpenEntity openEntity, Set<String> majors);

    /**
     * 查询开设信息
     * @param schoolYear
     * @param term
     * @return
     */
    List<OpenEntity> queryOpen(String schoolYear, int term);

    OpenEntity queryOpenByOpenId(int openId);
}
