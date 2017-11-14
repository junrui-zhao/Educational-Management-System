package com.crtvu.dao;

import com.crtvu.entity.Open;
import com.crtvu.entity.OpenEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by danagi on 2017/4/9.
 */
public interface OpenDAO {

    /**
     *
     * @param courseId
     * @param schoolYear
     * @param term
     * @param peopleNum
     * @param startSelectTime
     * @param endSelectTime
     */
    void insertOpen(@Param("courseId") String courseId, @Param("schoolYear") String schoolYear, @Param("term") int term, @Param("peopleNum") int peopleNum, @Param("startSelectTime") String startSelectTime, @Param("endSelectTime") String endSelectTime);

    /**
     *
     * @param openId
     * @param courseId
     * @param schoolYear
     * @param term
     * @param peopleNum
     * @param startSelectTime
     * @param endSelectTime
     * @return
     */
    int updateOpen(@Param("openId") int openId, @Param("courseId") String courseId, @Param("schoolYear") String schoolYear, @Param("term") int term, @Param("peopleNum") int peopleNum, @Param("startSelectTime") String startSelectTime, @Param("endSelectTime") String endSelectTime);

    /**
     *
     * @param openId
     * @return
     */
    int deleteOpen(@Param("openId") int openId);

    /**
     *
     * @param schoolYear
     * @param term
     * @return
     */
    List<OpenEntity> selectOpenBySchoolYearAndTerm(@Param("schoolYear") String schoolYear, @Param("term") int term);

    /**
     *
     * @return
     */
    int selectLastInsertId();

    OpenEntity selectOpenByOpenId(int openId);

    /**
     * 通过courseID查询open信息
     * @param courseId
     * @return
     */
    OpenEntity selectOpenByCourseId(String courseId);

    /**
     * 查询所有open信息
     * @return
     */
    List<OpenEntity> selectAllOpen();

    /**
     * 通过开设id查询开设信息
     * @param openId
     * @return
     */
    Open queryOpenById(int openId);

    /**
     * 通过学期学年查询开设id
     * @param term
     * @param year
     * @return
     */
    LinkedList<Open> queryOpenIdByTermAndYear(int term, String year);
    /**
     * 通过课程查询开设id
     * @param courseId
     * @returnid
     */
    Open queryOpenIdByCourseId(String courseId);
}
