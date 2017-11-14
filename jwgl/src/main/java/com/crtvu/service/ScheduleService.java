package com.crtvu.service;

import com.crtvu.entity.ClassroomEntity;
import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.ScheduleEntity;
import com.crtvu.entity.TeacherScheduleEntity;

import java.util.List;

/**
 * Created by danagi on 2017/4/10.
 */
public interface ScheduleService {

    enum ResultType{
        SUCCESS,
        KEY_FAIL,
        CLASH_FAIL,
        ID_FAIL,
        TIME_FAIL
    }

    class ScheduleResult{
        public ResultType resultType;
        public List<ScheduleEntity> scheduleEntities;

        public ScheduleResult(ResultType resultType, List<ScheduleEntity> scheduleEntities) {
            this.resultType = resultType;
            this.scheduleEntities = scheduleEntities;
        }

        public ResultType getResultType() {
            return resultType;
        }

        public void setResultType(ResultType resultType) {
            this.resultType = resultType;
        }

        public List<ScheduleEntity> getScheduleEntities() {
            return scheduleEntities;
        }

        public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
            this.scheduleEntities = scheduleEntities;
        }

        @Override
        public String toString() {
            return "ScheduleResult{" +
                    "resultType=" + resultType +
                    ", scheduleEntities=" + scheduleEntities +
                    '}';
        }
    }

    class ClassroomResult{
        public ResultType resultType;
        public List<ClassroomEntity> classroomEntities;

        public ClassroomResult(ResultType resultType, List<ClassroomEntity> classroomEntities) {
            this.resultType = resultType;
            this.classroomEntities = classroomEntities;
        }

        public ResultType getResultType() {
            return resultType;
        }

        public void setResultType(ResultType resultType) {
            this.resultType = resultType;
        }

        public List<ClassroomEntity> getClassroomEntities() {
            return classroomEntities;
        }

        public void setClassroomEntities(List<ClassroomEntity> classroomEntities) {
            this.classroomEntities = classroomEntities;
        }

        @Override
        public String toString() {
            return "ClassroomResult{" +
                    "resultType=" + resultType +
                    ", classroomEntities=" + classroomEntities +
                    '}';
        }
    }

    /**
     *
     * @param openEntity
     * @param teacherId
     * @param classroom
     * @param className
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     * @return
     */
    ScheduleResult addSchedule(OpenEntity openEntity, int teacherId, String classroom, String className, int day, int startWeek, int endWeek, int startTime, int endTime);

    /**
     *
     * @param id
     * @return
     */
    ResultType removeSchedule(int id);

    /**
     * 
     * @param scheduleEntity
     * @return
     */
    ScheduleResult modifySchedule(ScheduleEntity scheduleEntity);

    /**
     *
     * @param openId
     * @return
     */
    List<ScheduleEntity> queryCourseSchedule(int openId);

    /**
     *
     * @param teacherId
     * @param schoolYear
     * @param term
     * @return
     */
    List<TeacherScheduleEntity> queryTeacherSchedule(int teacherId, String schoolYear, int term);

    /**
     *
     * @param studentId
     * @param schoolYear
     * @param term
     * @return
     */
    List<ScheduleEntity> queryStudentSchedule(int studentId, String schoolYear, int term);

    /**
     *
     * @param major
     * @param schoolYear
     * @param term
     * @return
     */
    List<ScheduleEntity> queryMajorSchedule(String major, String schoolYear, int term);

    /**
     *
     * @param schoolYear
     * @param term
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     * @return
     */
    ClassroomResult queryFreeClassroom(String schoolYear, int term, int day, int startWeek, int endWeek, int startTime, int endTime);

    ScheduleEntity queryScheduleById(int id);
}
