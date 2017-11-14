package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.ReadExcel;
import com.crtvu.dto.student.CourseItem;
import com.crtvu.entity.*;
import com.crtvu.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by lcf12 on 2017/3/17.
 */
@Service
public class StudentServiceImpl implements StudentService {

    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String salt="1>#@42!dw1E?#sffffffsF@F#$4!@#2";

    @Autowired
    private StudentDAO studentDAO;
    private static final int pageNumber = 10;
    private static final int idLength = 1000000000;


    private static String COMPULSORY = "必修";

    private static String OPTIONAL = "选修";

    private static String MINOR = "辅修";

    @Autowired
    private StudentCourseDAO studentCourseDao;

    @Autowired
    private OpenDAO openDao;

    @Autowired
    private CourseDAO courseDao;

    @Autowired
    private ArrangementDAO arrangementDao;

    @Autowired
    private TargetMajorDAO targetMajorDao;

    @Autowired
    private StudentDAO studentDao;

    @Autowired
    private TermYearDAO termYearDao;

    private static int INSERT_SUCCESS = 1;

    public LinkedList<Course> getAllSelectCourse(int studentId) {
        LinkedList<Course> courseLinkedList = new LinkedList<Course>();
        LinkedList<StudentCourseEntity> studentCourseLinkedList = studentCourseDao.queryOpenIdByStudentId(studentId);
        for (StudentCourseEntity studentCourse : studentCourseLinkedList) {
            int openId = studentCourse.getOpenId();
            Course newCourse = courseDao.queryCourseById(openDao.queryOpenById(openId).getCourseId());
            courseLinkedList.add(newCourse);
        }
        return courseLinkedList;
    }

    public int getCurrentCourseLeftNum(int openId) {
        int peopleNum = openDao.queryOpenById(openId).getPeopleNum();
        LinkedList<StudentCourseEntity> studentCourseLinkedList = studentCourseDao.queryStudentsByOpenId(openId);
        return peopleNum - studentCourseLinkedList.size();
    }

    public Boolean isCourseConflict(Arrangement curArrangement, Arrangement selectArrangement) {
        if (curArrangement.getDay() != selectArrangement.getDay()) {
            return false;
        } else {
            if (selectArrangement.getStartWeek() > curArrangement.getEndWeek() || selectArrangement.getEndWeek() <= curArrangement.getStartWeek()) {
                return false;
            } else {
                if (selectArrangement.getStartTime() > curArrangement.getEndTime() || selectArrangement.getEndTime() < curArrangement.getStartTime()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int selectCourse(int studentId, int openId) {
        Open open = openDao.queryOpenById(openId);
        if (this.getCurrentCourseLeftNum(openId) == 0) {
            // 人数已满
            return 501;
        }
        LinkedList<Arrangement> curCourseArrangementLinkedList = arrangementDao.queryArrangementByOpenId(openId);
        LinkedList<StudentCourseEntity> studentCourseLinkedList = studentCourseDao.queryOpenIdByStudentId(studentId);
        for (StudentCourseEntity studentCourse : studentCourseLinkedList) {
            if (studentCourse.getOpenId() == openId) {
                // 同一课程已选择！
                return 502;
            }
            for (Arrangement curCourseArrangement : curCourseArrangementLinkedList) {
                LinkedList<Arrangement> selectCourseArrangementLinkedList = arrangementDao.queryArrangementByOpenId(studentCourse.getOpenId());
                for(Arrangement selectCourseArrangement : selectCourseArrangementLinkedList) {
                    if(isCourseConflict(curCourseArrangement, selectCourseArrangement)) {
                        // 时间冲突!
                        return 503;
                    }
                }
            }
        }

        // 检查数据库插入数据是否成功
        if (studentCourseDao.insertCourse(studentId, openId) == INSERT_SUCCESS) {
            // 选课成功
            return 500;
        } else {
            // 选课异常
            return 100;
        }
    }

    public int quitCourse(int studentId, int openId) {
        return studentCourseDao.quitCourse(studentId, openId);
    }

    public HashMap<String, LinkedList<CourseItem>> getAllCourse(int studentId) {
        HashMap<String, LinkedList<CourseItem>> courseMap = new HashMap<String, LinkedList<CourseItem>>();

        LinkedList<CourseItem> selectCompulsoryCourseLinkedList = new LinkedList<CourseItem>();
        LinkedList<CourseItem> selectOptionalCourseLinkedList = new LinkedList<CourseItem>();
        LinkedList<CourseItem> unselectCourseLinkedList = new LinkedList<CourseItem>();
        TermYearEntity termYear = termYearDao.queryCurrentTermAndSchoolYear();

        // 本专业选课
        List<TargetMajorEntity> targetMajorLinkedList = targetMajorDao.queryOpenIdByMajor(studentDao.queryStudentById(studentId).getMajor());
        for (TargetMajorEntity targetMajor : targetMajorLinkedList) {
            Open newOpen = openDao.queryOpenById(targetMajor.getOpenId());
            if (newOpen.getSchoolYear().equals(termYear.getSchoolYear()) && newOpen.getTerm() == termYear.getTerm()) {
                Course tmpCourse = courseDao.queryCourseById(newOpen.getCourseId());
                System.out.println(tmpCourse);
                CourseItem newCourse = new CourseItem(tmpCourse.getCourseId(), tmpCourse.getCourseName(), tmpCourse.getCredit(), tmpCourse.getNature(), tmpCourse.getDepartment(), newOpen.getOpenId(), newOpen.getPeopleNum(), this.getCurrentCourseLeftNum(newOpen.getOpenId()));
                if (studentCourseDao.queryStudentCourseByStudentAndOpenId(studentId, newOpen.getOpenId()) != null) {
                    if (newCourse.getNature().equals(COMPULSORY)) {
                        selectCompulsoryCourseLinkedList.add(newCourse);
                    } else {
                        selectOptionalCourseLinkedList.add(newCourse);
                    }
                } else {
                    unselectCourseLinkedList.add(newCourse);
                }
            }
        }
        courseMap.put("selectedcompulsory", selectCompulsoryCourseLinkedList);
        courseMap.put("selectedoptional", selectOptionalCourseLinkedList);
        courseMap.put("unselected", unselectCourseLinkedList);

        return courseMap;
    }

    public List<StudentEntity> getStudentList (int page,String studentProperty) {
        if(page<=0||page>getPageCount(studentProperty)) {
            return null;
        }
        return studentDAO.selectStudentByLimit(studentProperty,(page-1)*pageNumber,pageNumber);
    }

    public int getPageCount(String studentProperty) {
        int count = studentDAO.countAllStudent(studentProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    public StudentEntity getStudent(int id){
        return studentDAO.selectStudent(id);
    }

    public Result insertStudent(StudentEntity student) {
        if (student.getStudentId() <= 0 || student.getStudentId()/idLength == 0 || student.getStudentId()/idLength >= 10) {
            return Result.ID_LENGTH_FAIL;
        }
        try {
            String md5 = getMD5(student.getPassword());
            studentDAO.insertStudent(student.getStudentId(), student.getStudentName(), student.getClassName(), student.getMajor(), md5);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result deleteStudent(int id){
        return studentDAO.deleteStudent(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    public Result updateStudent(int id, String name, String className, String major){
        if (id <= 0 || id/idLength == 0 || id/idLength >= 10) {
            return Result.ID_LENGTH_FAIL;
        }
        int result;
        try {
           result = studentDAO.updateStudent(id, name, className, major);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

    public Result updateStudentPassword(int id, String newPassword) {
        int result;
        try {
            String md5;
            md5=getMD5(newPassword);
            result = studentDAO.updateStudentPassword(id,md5);
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

    public Map<String, Object> batchImport(String name,MultipartFile file){
        boolean b = false;
        Map<String, Object> modelMap = new HashMap<>();
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取学生信息集合。
        ErrorJson errorEntity = new ErrorJson();
        modelMap = readExcel.getExcelInfo(name ,file);
        List<StudentEntity> studentList = (List<StudentEntity>) modelMap.get("result");
        List<StudentEntity> removeList = new ArrayList();
        List<ErrorJson> errorList = (List<ErrorJson>) modelMap.get("error");
        if(studentList != null){
            //迭代添加学生信息（注：实际上这里也可以直接将studentList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for(StudentEntity student:studentList){
                if(this.getStudent(student.getStudentId())==null){
                    if(student.getStudentId()/idLength == 0) {
                        errorEntity = new ErrorJson();
                        errorEntity.setId(student.getStudentId());
                        errorEntity.setErrors("学号不足十位");
                    }else {
                        this.insertStudent(student);
                    }
                }
                else{
                    removeList.add(student);
                    errorEntity = new ErrorJson();
                    errorEntity.setId(student.getStudentId());
                    errorEntity.setErrors("学号已存在");
                    errorList.add(errorEntity);
                }
            }
        }

        if(removeList.size()>0)
        {
            studentList.removeAll(removeList);
        }
        modelMap.put("result",studentList);
        modelMap.put("error",errorList);
        System.out.println(studentList);
        return modelMap;
    }


    @Override
    public Set<String> getAllStudentClass() {
        Set<String> studentClassSet = new HashSet<>();
        List<StudentEntity> studentList = studentDAO.selectAllStudent();
        for (StudentEntity studentEntity : studentList) {
            studentClassSet.add(studentEntity.getClassName());
        }
        return studentClassSet;
    }
}
