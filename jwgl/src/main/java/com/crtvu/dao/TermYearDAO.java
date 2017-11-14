package com.crtvu.dao;

import com.crtvu.entity.TermYearEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Phoenix on 2017/4/11.
 */
public interface TermYearDAO {

    // 获取当前学年学期
    TermYearEntity queryCurrentTermAndSchoolYear();

    // 获取最近学年学期
    TermYearEntity queryRecentTermAndSchooYear();

    // 获取数据库中全部学年学期信息
    LinkedList<TermYearEntity> queryAllTermAndSchoolYear();

    /**
     * 添加学期实体
     * @param schoolYear
     * @param term
     * @param beginTime
     * @param overTime
     */
    void insertTerm(@Param("schoolYear") String schoolYear, @Param("term") int term, @Param("beginTime") Date beginTime, @Param("overTime") Date overTime);

    /**
     * 通过id删除学期
     * @param id
     * @return
     */
    int deleteTerm(@Param("id") int id);

    /**
     * 修改学期信息
     * @param schoolYear
     * @param term
     * @param beginTime
     * @param overTime
     * @param id
     * @return
     */
    int updateTerm(@Param("schoolYear") String schoolYear, @Param("term") int term, @Param("beginTime") Date beginTime, @Param("overTime") Date overTime, @Param("id") int id);

    /**
     * 查找某一id的学期
     * @param id
     * @return
     */
    TermYearEntity  selectTerm(@Param("id") int id);

    /**
     * 查找所有的学期
     * @return
     */
    List<TermYearEntity> selectAllTerm();
    /**
     * 对片段进行模糊查询并且分页
     * @return
     */
    List<TermYearEntity> selectTermByLimit(@Param("termProperty") String termProperty, @Param("index") int index, @Param("count") int count);
    /**
     * 查询学期数量
     * @return
     */
    int countAllTerm(String termProperty);

}
