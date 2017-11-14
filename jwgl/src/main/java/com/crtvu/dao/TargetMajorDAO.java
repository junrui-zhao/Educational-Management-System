package com.crtvu.dao;

import com.crtvu.entity.TargetMajorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Phoenix on 2017/4/4.
 */
public interface TargetMajorDAO {

    /**
     * 通过开设id查询面向专业信息
     * @param openId
     * @return
     */
    List<TargetMajorEntity> queryTargetMajorById(@Param("openId") int openId);

    /**
     * 通过专业名查询预置课程
     * @param major
     * @return
     */
    List<TargetMajorEntity> queryOpenIdByMajor(@Param("major") String major);

    /**
     *
     * @param openId
     * @param major
     */
    void insertTargetMajor(@Param("openId") int openId, @Param("major") String major);

    /**
     *
     * @param openId
     * @param major
     * @return
     */
    int deleteTargetMajor(@Param("openId") int openId, @Param("major") String major);

}
