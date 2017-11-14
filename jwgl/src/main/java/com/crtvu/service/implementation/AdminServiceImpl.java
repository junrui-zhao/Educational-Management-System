package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.admin.CourseSearch;
import com.crtvu.dto.admin.CourseStatiscisItem;
import com.crtvu.entity.*;
import com.crtvu.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

/**
 * Created by yangming on 2017/4/26/0026.
 */
@Service
public class AdminServiceImpl implements AdminService{
    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String salt="1>#@42!dw1E?#sffffffsF@F#$4!@#2";
    @Autowired
    private AdminDAO adminDAO;
    private static final int pageNumber = 10;
    private static final int idLength = 1000000000;

    @Autowired
    private OpenDAO openDao;

    @Autowired
    private CourseDAO courseDao;

    @Autowired
    private StudentCourseDAO studentCourseDao;

    @Autowired
    private TermYearDAO termYearDao;

    public int getCurrentCourseLeftNum(int openId) {
        int peopleNum = openDao.queryOpenById(openId).getPeopleNum();
        LinkedList<StudentCourseEntity> studentCourseLinkedList = studentCourseDao.queryStudentsByOpenId(openId);
        return peopleNum - studentCourseLinkedList.size();
    }

    /**
     * 获得选课信息
     *
     * @param term
     * @param year
     * @return
     */
    public LinkedList<CourseStatiscisItem> getSelectCourseInfoByTermAndYear(int term, String year) {
        LinkedList<CourseStatiscisItem> courseStatiscisItemLinkedList = new LinkedList<CourseStatiscisItem>();
        LinkedList<Open> openLinkedList = openDao.queryOpenIdByTermAndYear(term, year);
        for (Open open : openLinkedList) {
            String courseId = open.getCourseId();
            String courseName = courseDao.queryCourseById(courseId).getCourseName();
            int peopleNum = open.getPeopleNum();
            int leftNum = this.getCurrentCourseLeftNum(open.getOpenId());
            CourseStatiscisItem courseStatiscisItem = new CourseStatiscisItem(courseId, courseName, peopleNum, peopleNum-leftNum);
            courseStatiscisItemLinkedList.add(courseStatiscisItem);
        }
        return courseStatiscisItemLinkedList;
    }

    public LinkedList<TermYearEntity> getAllTermAndYear() {
        return termYearDao.queryAllTermAndSchoolYear();
    }

    public LinkedList<CourseSearch> getCourseSearchByCourseId(String courseId){
        LinkedList<CourseSearch> courseSearchLinkedList = new LinkedList<CourseSearch>();
        int peopleNum = openDao.queryOpenIdByCourseId(courseId).getPeopleNum();
        int openId = openDao.queryOpenIdByCourseId(courseId).getOpenId();
        String courseName = courseDao.queryCourseById(courseId).getCourseName();
        int leftNum =peopleNum - this.getCurrentCourseLeftNum((openId));
        CourseSearch courseSearch = new CourseSearch(courseName,peopleNum,leftNum);
        courseSearchLinkedList.add(courseSearch);
        return courseSearchLinkedList;
    }

    public List<AdminEntity> getAdminList (int page,String adminProperty) {
        if(page<=0||page>getPageCount(adminProperty)) {
            return null;
        }
        return adminDAO.selectAdminByLimit(adminProperty,(page-1)*pageNumber,pageNumber);
    }

    public int getPageCount(String adminProperty) {
        int count = adminDAO.countAllAdmin(adminProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    public AdminEntity getAdmin(int adminId){
        return adminDAO.selectAdmin(adminId);
    }

    public Result insertAdmin(AdminEntity admin) {
        if (admin.getAdminId()<= 0 || admin.getAdminId()/idLength == 0 || admin.getAdminId()/idLength >= 10) {
            return Result.ID_LENGTH_FAIL;
        }
        try {
            String md5 = getMD5(admin.getPassword());
            adminDAO.insertAdmin(admin.getAdminId(), md5);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result deleteAdmin(int adminId){
        return adminDAO.deleteAdmin(adminId)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    public Result updateAdminPassword(int adminId, String newPassword) {
        int result;
        try {
            String md5;
            md5=getMD5(newPassword);
            result = adminDAO.updateAdminPassword(adminId,md5);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;

    }
    public String getMD5(String password)
    {
        String base = password+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
