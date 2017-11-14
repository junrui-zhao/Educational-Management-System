package com.crtvu.dao;

import com.crtvu.entity.Classroom;
import com.crtvu.entity.ClassroomEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by danagi on 2017/4/6.
 */
public interface ClassroomDAO {
    /**
     * 添加教室
     * @param classroom
     * @param peopleNum
     */
    void insertClassroom(@Param("classroom") String classroom, @Param("peopleNum") int peopleNum);

    /**
     * 删除教室
     * @param classroom
     */
    int deleteClassroom(@Param("classroom") String classroom);

    /**
     * 更新教室
     * @param classroom
     * @param newRoomName
     * @param peopleNum
     */
    int updateClassroom(@Param("classroom") String classroom, @Param("newRoomName") String newRoomName, @Param("peopleNum") int peopleNum);

    /**
     * 通过教室名查询教室
     * @param classroom
     * @return
     */
    ClassroomEntity selectClassroom(@Param("classroom") String classroom);

    /**
     * 对片段进行模糊查询并且分页
     * @param index
     * @param count
     * @return
     */
    List<ClassroomEntity> selectClassroomByLimit(@Param("classroomProperty") String classroomProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 查询教室数量
     * @return
     */
    int countAllClassroom(String classroomProperty);

    /**
     *查询空教室
     * @param schoolYear
     * @param term
     * @param day
     * @param startWeek
     * @param endWeek
     * @param startTime
     * @param endTime
     * @return
     */
    List<ClassroomEntity> selectFreeClassroom(@Param("schoolYear") String schoolYear, @Param("term") int term, @Param("day") int day, @Param("startWeek") int startWeek,
                                              @Param("endWeek") int endWeek, @Param("startTime") int startTime, @Param("endTime") int endTime);

    /**
     * 查找所有的教室
     * @return
     */
    List<ClassroomEntity> selectAllClassroom();

    /**
     * 通过教室名称查询教室信息
     * @param name
     * @return
     */
    Classroom queryClassroomByName(String name);
}
