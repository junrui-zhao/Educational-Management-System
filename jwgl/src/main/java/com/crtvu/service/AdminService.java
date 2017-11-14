package com.crtvu.service;

import com.crtvu.dto.admin.CourseSearch;
import com.crtvu.dto.admin.CourseStatiscisItem;
import com.crtvu.entity.AdminEntity;
import java.util.*;

import com.crtvu.entity.TermYear;
import com.crtvu.entity.TermYearEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangming on 2017/4/25/0025.
 */
public interface AdminService {
    enum Result{
        SUCCESS,//成功
        ID_FAIL,//id重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }
    /**
     * 根据页码查询教师列表
     * @param page
     * @param adminProperty
     * @return
     */
    List<AdminEntity> getAdminList(int page, String adminProperty);

    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String adminProperty);

    /**
     * 找到指定id的管理员
     * @param adminId
     * @return
     */
    AdminEntity getAdmin(int adminId);

    /**
     * 添加新管理员
     * @param admin
     */
    Result insertAdmin(AdminEntity admin);

    /**
     * 删除某一ID的管理员
     * @param adminId
     */
    Result deleteAdmin(int adminId);

    /**
     * 修改密码
     * @param adminId
     * @param newPassword
     */
    Result updateAdminPassword(int adminId, String newPassword);

    /**
     * 获得选课信息
     * @param term
     * @param year
     * @return
     */
    LinkedList<CourseStatiscisItem> getSelectCourseInfoByTermAndYear(int term, String year);

    LinkedList<TermYearEntity> getAllTermAndYear();

    LinkedList<CourseSearch> getCourseSearchByCourseId(String courseId);

}
