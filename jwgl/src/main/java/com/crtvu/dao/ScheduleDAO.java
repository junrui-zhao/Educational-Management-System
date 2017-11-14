package com.crtvu.dao;

import com.crtvu.entity.ScheduleEntity;
import com.crtvu.entity.TeacherScheduleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by danagi on 2017/4/9.
 */
public interface ScheduleDAO {
    /**
     *
     * @param openId
     * @param teacherId
     * @param classroom
     * @param className
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     */
    void insertSchedule(@Param("openId") int openId, @Param("teacherId") int teacherId, @Param("classroom") String classroom, @Param("className") String className,
                        @Param("day") int day, @Param("startWeek") int startWeek, @Param("endWeek") int endWeek, @Param("startTime") int startTime, @Param("endTime") int endTime);

    /**
     *
     * @param id
     * @return
     */
    int deleteSchedule(@Param("id") int id);

    /**
     *
     * @param id
     * @param openId
     * @param teacherId
     * @param classroom
     * @param className
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     */
    int updateSchedule(@Param("id") int id, @Param("openId") int openId, @Param("teacherId") int teacherId, @Param("classroom") String classroom, @Param("className") String className,
                       @Param("day") int day, @Param("startWeek") int startWeek, @Param("endWeek") int endWeek, @Param("startTime") int startTime, @Param("endTime") int endTime);

    /**
     *
     * @param teacherId
     * @param classroom
     * @param schoolYear
     * @param term
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     * @return
     */
    List<ScheduleEntity> selectClashSchedule(@Param("teacherId") int teacherId, @Param("classroom") String classroom, @Param("schoolYear") String schoolYear, @Param("term") int term,
                                             @Param("day") int day, @Param("startWeek") int startWeek, @Param("endWeek") int endWeek, @Param("startTime") int startTime, @Param("endTime") int endTime, @Param("className") String className, @Param("openId") int openId);

    /**
     *
     * @param openId
     * @return
     */
    List<ScheduleEntity> selectScheduleByOpenId(@Param("openId") int openId);

    /**
     *
     * @param teacherId
     * @param schoolYear
     * @param term
     * @return
     */
    List<TeacherScheduleEntity> selectScheduleByTeacherId(@Param("teacherId") int teacherId, @Param("schoolYear") String schoolYear, @Param("term") int term);

    /**
     *
     * @param studentId
     * @param schoolYear
     * @param term
     * @return
     */
    List<ScheduleEntity> selectScheduleByStudentId(@Param("studentId") int studentId, @Param("schoolYear") String schoolYear, @Param("term") int term);

    /**
     *
     * @param major
     * @param schoolYear
     * @param term
     * @return
     */
    List<ScheduleEntity> selectScheduleByMajor(@Param("major") String major, @Param("schoolYear") String schoolYear, @Param("term") int term);

    ScheduleEntity selectScheduleByArrangementId(@Param("arrangementId") int arrangementId);
}
