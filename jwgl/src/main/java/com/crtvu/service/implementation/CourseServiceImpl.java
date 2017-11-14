package com.crtvu.service.implementation;


import com.crtvu.dto.ErrorCJson;
import com.crtvu.dto.ReadCExcel;
import com.crtvu.entity.CourseEntity;
import com.crtvu.service.CourseService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crtvu.dao.CourseDAO;
import org.springframework.util.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangming on 2017/3/16/0016.
 */
@Service
public class CourseServiceImpl implements CourseService {
    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseDAO courseDAO;
    private static final int pageNumber = 10;
    private static final int idLength = 1000000000;
    public List<CourseEntity> getCourseList (int page,String courseProperty) {
        if(page<=0||page>getPageCount(courseProperty)) {
            return null;
        }
        return courseDAO.selectCourseByLimit(courseProperty,(page-1)*pageNumber,pageNumber);
    }
    public int getPageCount(String courseProperty) {
        int count = courseDAO.countAllCourse(courseProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }



    public CourseEntity getCourse(String courseId) {
        return courseDAO.selectCourse(courseId);
    }

    public List<CourseEntity> getAllCourseList() {
        return courseDAO.selectAllCourse();
    }

    public Result insertCourse(CourseEntity course) {
        if ( course.getCourseId().length()!= 10 ) {
            return Result.ID_LENGTH_FAIL;
        }
        if(course.getCredit() >= 10 || course.getCredit() < 0){
            return Result.CREDIT_FAIL;
        }
        if(!course.getNature().equals("必修") &&!course.getNature().equals("选修")&& !course.getNature().equals("辅修")){
            return Result.NATURE_FAIL;
        }
        if(!course.getDepartment().equals("化学工程学院") && !course.getDepartment().equals("学生工作办公室") && !course.getDepartment().equals("信息科学与技术学院")
                && !course.getDepartment().equals("文法学院")&& !course.getDepartment().equals("职业技术学院")&& !course.getDepartment().equals("材料科学与工程学院")
                &&course.getDepartment().equals("经济管理学院") && !course.getDepartment().equals("生命科学与技术学院")&&!course.getDepartment().equals("马克思主义学院")
                &&!course.getDepartment().equals("机电工程学院") &&!course.getDepartment().equals("理学院") &&!course.getDepartment().equals("国际教育学院")
                &&!course.getDepartment().equals("继续教育学院") ){
            return Result.DEPARTMENT_FAIL;
        }
        try {
            courseDAO.insertCourse(course.getCourseId(),course.getCourseName(), course.getCredit() , course.getNature(), course.getDepartment());
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result insertCourseNoCre(CourseEntity course) {
        if ( course.getCourseId().length()!= 10 ) {
            return Result.ID_LENGTH_FAIL;
        }
        if(!course.getNature().equals("必修") &&!course.getNature().equals("选修")&& !course.getNature().equals("辅修")){
            return Result.NATURE_FAIL;
        }
        if(!course.getDepartment().equals("化学工程学院") && !course.getDepartment().equals("学生工作办公室") && !course.getDepartment().equals("信息科学与技术学院")
                && !course.getDepartment().equals("文法学院")&& !course.getDepartment().equals("职业技术学院")&& !course.getDepartment().equals("材料科学与工程学院")
                &&course.getDepartment().equals("经济管理学院") && !course.getDepartment().equals("生命科学与技术学院")&&!course.getDepartment().equals("马克思主义学院")
                &&!course.getDepartment().equals("机电工程学院") &&!course.getDepartment().equals("理学院") &&!course.getDepartment().equals("国际教育学院")
                &&!course.getDepartment().equals("继续教育学院") ){
            return Result.DEPARTMENT_FAIL;
        }
        try {
            courseDAO.insertCourseNoCre(course.getCourseId(),course.getCourseName(), course.getNature(), course.getDepartment());
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result deleteCourse(String id) {
        return courseDAO.deleteCourse(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    public Result updateCourse( String name, Float credit, String nature, String department,String id) {
        if ( id.length()!= 10 ) {
            return Result.ID_LENGTH_FAIL;
        }
        if(credit >= 10 || credit < 0){
            return Result.CREDIT_FAIL;
        }
        if(!nature.equals("必修") &&!nature.equals("选修")&& !nature.equals("辅修")){
            return Result.NATURE_FAIL;
        }
        if(!department.equals("化学工程学院") && !department.equals("学生工作办公室") && !department.equals("信息科学与技术学院")
                && !department.equals("文法学院")&& !department.equals("职业技术学院")&& !department.equals("材料科学与工程学院")
                &&department.equals("经济管理学院") && !department.equals("生命科学与技术学院")&&!department.equals("马克思主义学院")
                &&!department.equals("机电工程学院") &&!department.equals("理学院") &&!department.equals("国际教育学院")
                &&!department.equals("继续教育学院") ){
            return Result.DEPARTMENT_FAIL;
        }
        int result;
        try {
            result = courseDAO.updateCourse(name,credit,nature,department,id);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;


    }
    public Result updateCourseNoCre( String name, String nature, String department,String id) {
        if ( id.length()!= 10 ) {
            return Result.ID_LENGTH_FAIL;
        }
        if(!nature.equals("必修") &&!nature.equals("选修")&& !nature.equals("辅修")){
            return Result.NATURE_FAIL;
        }
        if(!department.equals("化学工程学院") && !department.equals("学生工作办公室") && !department.equals("信息科学与技术学院")
                && !department.equals("文法学院")&& !department.equals("职业技术学院")&& !department.equals("材料科学与工程学院")
                &&department.equals("经济管理学院") && !department.equals("生命科学与技术学院")&&!department.equals("马克思主义学院")
                &&!department.equals("机电工程学院") &&!department.equals("理学院") &&!department.equals("国际教育学院")
                &&!department.equals("继续教育学院") ){
            return Result.DEPARTMENT_FAIL;
        }
        int result;
        try {
            result = courseDAO.updateCourseNoCre(name,nature,department,id);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }


    public Map<String, Object> batchImport(String name, MultipartFile file){
        Map<String, Object> modelMap = new HashMap<>();
        //创建处理EXCEL
        ReadCExcel readCExcel=new ReadCExcel();
        //解析excel，获取学生信息集合。
        ErrorCJson errorEntity = new ErrorCJson();
        modelMap = readCExcel.getExcelInfo(name ,file);
        List<CourseEntity> courseList = (List<CourseEntity>) modelMap.get("result");
        List<CourseEntity> removeList = new ArrayList();
        List<ErrorCJson> errorList = (List<ErrorCJson>) modelMap.get("error");
        if(courseList != null){
            System.out.println("33333333333333333333");
            System.out.println(courseList);

            //迭代添加学生信息（注：实际上这里也可以直接将studentList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for(CourseEntity course:courseList){
                System.out.println(44444);
                if(this.getCourse(course.getCourseId())==null){
                    if(course.getCourseId().length()!=10) {
                        errorEntity = new ErrorCJson();
                        errorEntity.setCourseId(course.getCourseId());
                        errorEntity.setErrors("课号不满足10位");
                    }else {
                        System.out.println("添加成功");
                        System.out.println(course);
                        Result result = this.insertCourse(course);
                        System.out.println(result);
                    }
                }
                else{
                    removeList.add(course);
                    errorEntity = new ErrorCJson();
                    errorEntity.setCourseId(course.getCourseId());
                    errorEntity.setErrors("课号已存在");
                    errorList.add(errorEntity);
                }
            }
            System.out.println(5555555);
            if(removeList.size()>0)
            {
                courseList.removeAll(removeList);
            }
            modelMap.put("result",courseList);
            modelMap.put("error",errorList);
            System.out.println(courseList);
            System.out.println("22222222222222222");
        }

        return modelMap;
    }
}
