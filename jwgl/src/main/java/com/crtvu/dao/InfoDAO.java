package com.crtvu.dao;

import com.crtvu.entity.InfoEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by yangming on 2017/4/27/0027.
 */
public interface InfoDAO {
    /**
     * 添加通知实体
     * @param studentId
     * @param title
     * @param context
     * @param time
     * @param sender
     */
    void insertInfo(@Param("title") String title, @Param("context") String context, @Param("time") Timestamp time, @Param("sender") String sender, @Param("studentId") int studentId);

    /**
     * 删除通知
     * @param id
     * @return
     */
    int deleteInfo(@Param("id") int id);

    /**
     * 修改通知
     * @param studentId
     * @param id
     * @param title
     * @param context
     * @param time
     * @param sender
     * @return
     */
    int updateInfo(@Param("id") int id, @Param("title") String title, @Param("context") String context, @Param("time") Timestamp time, @Param("sender") String sender, @Param("studentId") int studentId);

    /**
     * 查找通知
     * @param id
     * @return
     */
    InfoEntity selectInfo(@Param("id") int id);

    /**
     * 所有通知
     * @return
     */
    List<InfoEntity> selectAllInfo();
    /**
     * 对片段进行模糊查询并且分页
     * @return
     */
    List<InfoEntity> selectInfoByLimit(@Param("infoProperty") String infoProperty, @Param("index") int index, @Param("count") int count);
    /**
     * 查询课程数量
     * @return
     */
    int countAllInfo(String infoProperty);
    /**
     *按学生id查询
     * @param studentId
     * @return
     */
    List<InfoEntity> selectRecentInfoByStudentId(@Param("studentId") int studentId);

}
