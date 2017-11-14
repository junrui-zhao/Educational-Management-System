package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.teacher.TeachCourseItem;
import com.crtvu.dto.teacher.TeachStudentItem;
import com.crtvu.entity.*;
import com.crtvu.service.TeacherService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by gao27024037 on 2017/3/24.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private TeacherCourseDAO teacherCourseDao;

    @Autowired
    private ArrangementDAO arrangementDao;

    @Autowired
    private OpenDAO openDao;

    @Autowired
    private CourseDAO courseDao;

    @Autowired
    private StudentCourseDAO studentCourseDao;

    @Autowired
    private StudentDAO studentDao;

    public LinkedList<TeachCourseItem> getAllTeachCourseById(int teacherId) {
        LinkedList<TeachCourseItem> teachCourseItemLinkedList = new LinkedList<TeachCourseItem>();
        LinkedList<Arrangement> arrangementLinkedList = arrangementDao.queryArrangementByTeacherId(teacherId);
        for (Arrangement arrangement : arrangementLinkedList) {
            Open open = openDao.queryOpenById(arrangement.getOpenId());
            Course course = courseDao.queryCourseById(open.getCourseId());
            String classTime = TeachCourseItem.reamkeClassTime(arrangement.getStartWeek(), arrangement.getEndWeek(), arrangement.getStartTime(), arrangement.getEndTime());
            TeachCourseItem teachCourseItem = new TeachCourseItem(course.getCourseName(), arrangement.getClassroom(), arrangement.getDay(), classTime);
            teachCourseItemLinkedList.add(teachCourseItem);
        }
        return teachCourseItemLinkedList;
    }

    public HashMap<Integer, LinkedList<TeachStudentItem>> getAllCourseStudentList(int teacherId) {
        HashMap<Integer, LinkedList<TeachStudentItem>> teachStudentMap = new HashMap<Integer, LinkedList<TeachStudentItem>>();
        // 通过教师id查询openId
        LinkedList<TeacherCourse> teacherCourseLinkedList = teacherCourseDao.queryTeacherCourseByTeacherId(teacherId);
        for (TeacherCourse teacherCourse : teacherCourseLinkedList) {
            LinkedList<TeachStudentItem> teachStudentItemLinkedList = new LinkedList<TeachStudentItem>();
            // 通过开设id查询选课学生学号
            LinkedList<StudentCourseEntity> studentCoursesLinkedList = studentCourseDao.queryStudentsByOpenId(teacherCourse.getOpenId());
            for (StudentCourseEntity studentCourse : studentCoursesLinkedList) {
                // 获得学生对象
                Student student = studentDao.queryStudentById(studentCourse.getStudentId());
                // 学生学号
                int studentId = student.getStudentId();
                // 学生姓名
                String studentName = student.getStudentName();
                // 学生班级
                String studentClassName = student.getClassName();
                // 学生专业
                String studentMajorName = student.getMajor();
                // 课程名称
                String courseName = courseDao.queryCourseById(openDao.queryOpenById(teacherCourse.getOpenId()).getCourseId()).getCourseName();

                TeachStudentItem teachStudentItem = new TeachStudentItem(studentId, studentName, studentClassName, studentMajorName, courseName);
                teachStudentItemLinkedList.add(teachStudentItem);
            }
            teachStudentMap.put(teacherCourse.getOpenId(), teachStudentItemLinkedList);
        }
        return teachStudentMap;
    }

    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String salt = "1>#@42!dw1E?#sffffffsF@F#$4!@#2";
    private static final int pageNumber = 10;
    private static final int idLength = 1000000000;

    //计算总共的页数
    public int getPageCount(String studentProperty) {
        int count = teacherDAO.countAllTeacher(studentProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    public List<TeacherEntity> getTeacherList() {
        return teacherDAO.selectAllTeacher();

    }

    //查所有老师
    public List<TeacherEntity> getTeacherList(int page,String teacherProperty) {
        if(page<=0||page>getPageCount(teacherProperty)) {
            return null;
        }
        return teacherDAO.selectTeacherByLimit(teacherProperty,(page-1)*pageNumber,pageNumber);
    }


    //根据ID获取教师信息
    public TeacherEntity getTeacher(int id) {
        return teacherDAO.selectTeacher(id);
    }

    //修改教师信息
    public Result updateTeacher(int id, String name, String title) {
        if (id <= 0 || id/idLength == 0|| id/idLength >= 10) {
            return Result.ID_LENGTH_FAIL;
        }
        int result;
        try {
            result = teacherDAO.updateTeacher(id, name, title);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

    //修改密码
    public Result updateTeacherPassword(int id, String password) {
        int result;
        try {
            result = teacherDAO.updateTeacherPassword(id,getMD5(password));
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

    //MD5加密算法
    public String getMD5(String teacherPassword) {
        String base = teacherPassword + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //根据ID删除教师
    public Result deleteTeacher(int id) {
        return teacherDAO.deleteTeacher(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    //增添新教师
    public Result insertTeacher(TeacherEntity teacherEntity) {
        if (teacherEntity.getTeacherId() <= 0 || teacherEntity.getTeacherId()/idLength == 0 || teacherEntity.getTeacherId()/idLength >= 10) {
            return Result.ID_LENGTH_FAIL;
        }
        try {
            teacherEntity.setPassword(getMD5(teacherEntity.getPassword()));
            teacherDAO.insertTeacher(teacherEntity.getTeacherId(),teacherEntity.getTeacherName(),teacherEntity.getTitle(),teacherEntity.getPassword());

        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    //产生报表 用于用户下载
    public HSSFWorkbook creatTable(){
        HSSFWorkbook webbook = new HSSFWorkbook();
        HSSFSheet sheet = webbook.createSheet("教师表");
        HSSFCellStyle style = webbook.createCellStyle();
        sheet.setColumnWidth(0,4500);
        sheet.setColumnWidth(1,4500);
        sheet.setColumnWidth(2,4500);
        HSSFRow row = sheet.createRow(0);   //--->创建一行
        row.createCell(0).setCellValue("工号(10位数字)"); //--->创建一个单元格
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("职称");
        return webbook;
    }

    //处理用户上传的报表
    public  List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(fileType)){
            wb = new HSSFWorkbook(in);
        }else if(".xlsx".equals(fileType)){
            wb = new XSSFWorkbook(in);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        if(null == wb){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        sheet = wb.getSheetAt(0);
        for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
            row = sheet.getRow(r);
            if(row==null||row.getFirstCellNum()==r) {
                continue;
            }
            //遍历所有的列
            List<Object> li = new ArrayList<Object>();
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                li.add(this.getCellValue(cell));
            }
            list.add(li);
        }
        //wb.close();
        list = this.insertList(list);
        return list;
    }

    //对报表数据依次添加并加上反馈情况
    public List<List<Object>> insertList(List<List<Object>> list) {
        String password = "00000000";
        for(int i = 0; i < list.size(); i++) {
            String error = "";
            if (list.get(i).size() < 3) {
                for (int j = list.get(i).size(); j <= 3; j++)
                    list.get(i).add(j,"");
                error += "数据不能为空 ";
            } else if (!(list.get(i).get(0).toString().length() == 10
                    && list.get(i).get(0).toString().matches("[0-9]+"))) {
                error += "工号请输入十位数字 ";
            } else if (this.getTeacher(Integer.valueOf(list.get(i).get(0).toString())) != null) {
                error += "该工号已存在 ";
            }
            if (error.equals("")) {
                Result flag = this.insertTeacher(new TeacherEntity(Integer.valueOf(list.get(i).get(0).toString()),
                        list.get(i).get(1).toString(),
                        list.get(i).get(2).toString(),
                        password));

                if (flag == Result.ID_FAIL) {
                    list.get(i).add(3, "id已存在");
                } else if(flag == Result.ID_LENGTH_FAIL) {
                    list.get(i).add(3, "id长度不合规定");
                }else{
                        list.get(i).add(3, "1");
                    }
                }
             else {
                list.get(i).add(3, error);
            }
        }
        return list;
    }

    //根据cell类型获取value
    public Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

}
