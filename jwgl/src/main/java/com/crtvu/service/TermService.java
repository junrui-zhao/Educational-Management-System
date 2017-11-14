package com.crtvu.service;

import com.crtvu.entity.TermYearEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

/**
 * Created by yangming on 2017/4/25/0025.
 */
public interface TermService {
    enum Result{
        SUCCESS,//成功
        SCHOOLYEAR_FAIL,
        BEGINTIME_FAIL,//开始时间
        OVERTIME_FAIL,//结束时间
        TERM_FAIL,//学期
        ID_FAIL,//id号重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }

    /**
     * 对课程信息进行分页，并可以翻到第page
     * @param page
     * @return
     */
    List<TermYearEntity> getTermList(int page, String termProperty);
    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String termProperty);

    /**
     * 找到指定id的学期
     * @param id
     * @return
     */
    TermYearEntity getTerm(int id);
    /**
     * 添加
     * @param term
     */
    Result insertTerm(TermYearEntity term);
    /**
     * 删除某一ID
     * @param id
     */
    Result deleteTerm(int id);

    /**
     * 修改学期信息
     * @param schoolYear
     * @param term
     * @param beginTime
     * @param overTime
     * @param id
     * @return
     */
    Result updateTerm(String schoolYear, int term, Date beginTime, Date overTime, int id);

}
