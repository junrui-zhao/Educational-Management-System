package com.crtvu.service.implementation;

import com.crtvu.dao.*;
import com.crtvu.dto.CourseOpenInfo;
import com.crtvu.dto.teacher.*;
import com.crtvu.entity.CourseEntity;
import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.SelectCourseEntity;
import com.crtvu.entity.TeacherEntity;
import com.crtvu.service.TeacherGradeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by x6012 on 2017/3/23.
 */
@Service
public class TeacherGradeServiceImpl implements TeacherGradeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SelectCourseDAO selectCourseDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private TeachCourseDAO teachCourseDAO;

    @Autowired
    private OpenDAO openDAO;

    /**
     *
     * @param teacherId
     * @return
     */
    public TeacherAllCourse showTeacherAllCourseInfo(int teacherId) {
        TeacherEntity teacherEntity = teacherDAO.selectTeacher(teacherId);
        List<Integer> opens = teachCourseDAO.getAllOpenId(teacherId);
        List<CourseOpenInfo> courseOpenInfoList = new ArrayList<CourseOpenInfo>();
        for(int temp : opens){
            OpenEntity openEntity = openDAO.selectOpenByOpenId(temp);
            String courseId = openEntity.getCourseId();
            CourseEntity courseEntity = courseDAO.selectCourse(courseId);
            CourseOpenInfo courseOpenInfo =new CourseOpenInfo(courseId, openEntity.getOpenId(), courseEntity.getCourseName(), courseEntity.getCredit(),
                    courseEntity.getNature(), courseEntity.getDepartment(), openEntity.getSchoolYear(), openEntity.getTerm(), openEntity.getPeopleNum());
            courseOpenInfoList.add(courseOpenInfo);
            //CourseEntity courseEntity = courseDAO.getCourseInfoByCourseId()
        }
        return new TeacherAllCourse(teacherEntity.getTeacherId(), teacherEntity.getTeacherName(), teacherEntity.getTitle(),courseOpenInfoList);
        //CourseEntity course = courseDAO.getCourseInfoByCourseId(courseId);
    }

    public List<SelectCourseEntity>  queryCourseByOpenid (int openId) {
         return selectCourseDAO.selectGradeByOpenId(openId);
    }

    public List<StuGrade> getStudentGrade(int openId) {
        List<StuGrade> stuGrades = new ArrayList<StuGrade>();
        List<SelectCourseEntity> selectCourseEntities = selectCourseDAO.selectGradeByOpenId(openId);
        for(SelectCourseEntity temp: selectCourseEntities){
            int studentId = temp.getStudentId();
            String stuName = studentDAO.selectStudent(studentId).getStudentName();
            Float grade = temp.getGrade();
            StuGrade stuGrade = new StuGrade(studentId,stuName,openId,grade);
            stuGrades.add(stuGrade);
        }
        return stuGrades;
    }

    public int updateGrade(List<UpdateGrade> updateGradeList) {
        for(UpdateGrade temp:updateGradeList){
            selectCourseDAO.updateGrade(temp.getStudentId(),temp.getOpenId(),temp.getGrade());
        }
        return 1;
    }

    private String buildGradeExcel(List<StudentGradeForDownload> list, String fileName) {

        String path = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("/WEB-INF/classes/", "");
        //path://C:~/jwgl/target/jwgl/
        //设置目录下的download目录

        //生成表格
        String columnName[]={"学号","姓名","班级","专业","成绩"};

        //创建目录
        String filePath = path + "/download/teacher/";
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }

        HSSFWorkbook book = new HSSFWorkbook();
        // 生成名为“sheet1”的工作表
        HSSFSheet sheet =book.createSheet("sheet1");
        //设置学号这列宽为10.
        //sheet.setColumnWidth(0,11);
        //表头导航
        HSSFRow row0 = sheet.createRow(0);
        for (int i=0;i<5;i++){
            row0.createCell(i).setCellValue(columnName[i]);
        }
        for(int i=0;i<list.size();i++){
            HSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(String.valueOf(list.get(i).getStuId()));
            row.createCell(1).setCellValue(String.valueOf(list.get(i).getStuName()));
            row.createCell(2).setCellValue(String.valueOf(list.get(i).getStuClass()));
            row.createCell(3).setCellValue(String.valueOf(list.get(i).getStuMajor()));
            if(list.get(i).getGrade()==null)
                row.createCell(4).setCellValue("null");
            else
                row.createCell(4).setCellValue(String.valueOf(list.get(i).getGrade()));
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filePath+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
            //生成excel文件!
            book = Workbook.createWorkbook(new File(filePath+fileName));
            // 生成名为“sheet1”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("sheet1",0);
            //表头导航
            for (int i=0;i<5;i++){
                Label label = new Label(i,0,columnName[i]);
                sheet.addCell(label);
            }
            //设置学号这列宽为10,不知什么原因 >10才能正确显示
            sheet.setColumnView(0,11);
            for (int i=0;i<list.size();i++){
                //为了显示美观，将数字转为文本格式来显示
                //sheet.addCell(new Number(0,i+1,list.get(i).getStuId()));
                sheet.addCell(new Label(0,i+1,String.valueOf(list.get(i).getStuId())));
                sheet.addCell(new Label(1,i+1,list.get(i).getStuName()));
                sheet.addCell(new Label(2,i+1,list.get(i).getStuClass()));
                sheet.addCell(new Label(3,i+1,list.get(i).getStuMajor()));
                if(list.get(i).getGrade()==null)
                    sheet.addCell(new Label(4,i+1,"null"));
                else
                    sheet.addCell(new Label(4,i+1,String.valueOf(list.get(i).getGrade())));
                //sheet.addCell(new Number(4,i+1,list.get(i).getGrade()));
                //String.valueOf(list.get(i).getGrade()))
            }
            book.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            if(book!=null)
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
        }
        */
        return fileName;
    }

    public String download(int teacherId,int openId) {
        //需要先判断是否有资格下载

        if (!teachCourseDAO.getAllOpenId(teacherId).contains(openId))
            return "error";
        else
        {
            //生成文件名称 样式 "高等数学-20170402.xls"
            Date date = new Date();
            SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");
            String d =df.format(date);
            String courseId = openDAO.selectOpenByOpenId(openId).getCourseId();
            String courseName = courseDAO.selectCourse(courseId).getCourseId();
            String fileName = courseName+"-"+d+".xls";

            //拼接数据
            List<StudentGradeForDownload> list = new ArrayList<StudentGradeForDownload>();
            List<SelectCourseEntity> selectCourseEntityList = selectCourseDAO.selectGradeByOpenId(openId);
            for(SelectCourseEntity temp: selectCourseEntityList){
                int stuId = temp.getStudentId();
                Float grade = temp.getGrade();
                String stuName = studentDAO.selectStudent(stuId).getStudentName();
                String stuClass = studentDAO.selectStudent(stuId).getClassName();
                String stuMajor = studentDAO.selectStudent(stuId).getMajor();
                StudentGradeForDownload tempEntity = new StudentGradeForDownload(stuId,stuName,stuClass,stuMajor,grade);
                list.add(tempEntity);
            }
            String filename = buildGradeExcel(list,fileName);
            return filename;
        }


    }

    public Map<String, Object> readNetworkExcel(MultipartFile multipartFile,int openId) throws IOException {
        //检查文件格式是否正确；
        //返回信息初始化
        Map<String,Object> map =new HashMap<String ,Object>();
        List<String> errorList = new ArrayList<String>();

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile localFile = (CommonsMultipartFile)multipartFile;
        String path = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("/WEB-INF/classes/", "")+"/upload";
        //path://C:~/jwgl/target/jwgl/upload
        //创建目录
        File filePath = new File(path);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        //将上传的文件写入新建的文件中,要判断是.xls或者.xlsx
        String multipartFileName = multipartFile.getOriginalFilename();
        File file = null;
        if(multipartFileName.endsWith(".xls")){
            file = new File(path+"/"+new Date().getTime()+".xls");
        } else{
            file = new File(path+"/"+new Date().getTime()+".xlsx");
        }
        try {
            localFile.getFileItem().write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<UploadGrade> uploadGradeList = new ArrayList<UploadGrade>();

        InputStream is = new FileInputStream(file);
        Workbook excel = null;
        if(multipartFileName.endsWith(".xls")){
            excel = new HSSFWorkbook(is);
        }


        Sheet sheet = excel.getSheetAt(0);
        //int columns = sheet.getColumns();
        if(sheet.getRow(0).getPhysicalNumberOfCells()==2){
            int i;
            for(i = 1; i<sheet.getPhysicalNumberOfRows();i++){
                Row row = sheet.getRow(i);
                int studentId;
                float grade;
                if(row.getCell(0).getCellType()== Cell.CELL_TYPE_NUMERIC){
                    studentId = (int) row.getCell(0).getNumericCellValue();
                    if((studentId/1000000000)<1||(studentId/1000000000)>10){
                        errorList.add("可能是第"+i+"行第1列:["+row.getCell(0)+"]出现了错误：学号长度错误");
                        continue;
                    }
                }else {
                    errorList.add("可能是第"+i+"行第1列:["+row.getCell(0)+"]出现了错误：请检查学号是否正确");
                    continue;
                }
                if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC){
                    grade = (float) row.getCell(1).getNumericCellValue();
                    if(grade<0||grade>100){
                        errorList.add("可能是第"+i+"行第2列出现了错误：["+row.getCell(1)+"]成绩应在0~100之间");
                        continue;
                    }
                }else {
                    errorList.add("可能是第"+i+"行第2列出现了错误：["+row.getCell(1)+"]数据格式错误");
                    continue;
                }
                UploadGrade uploadGrade = new UploadGrade(studentId,grade);
                uploadGradeList.add(uploadGrade);
            }
        }else {
            String columnsError = "columnsError:可能是列数目不正确，请参考报表格式";
            errorList.add(columnsError);
        }
        //System.out.println(uploadGradeList);
        is.close();
        if(errorList.size()==0) {
            //map.put("uploadList",uploadGradeList);
            List<String> list = uploadCheck(openId,uploadGradeList);
            errorList.addAll(list);
        }
        map.put("errorList",errorList);
        return map;
    }

    private List<String> uploadCheck(int openId,List<UploadGrade> uploadList){
        //检查插入是否满足要求：必须是这个老师的这门课才能插入
        //检查学号是否正确等；
        List<SelectCourseEntity> queryList = selectCourseDAO.selectGradeByOpenId(openId);
        List<Integer> stuIdList = new ArrayList<Integer>();
        for(int i = 0;i<queryList.size();i++){
            stuIdList.add(queryList.get(i).getStudentId());
        }
        List<String> errorList = new ArrayList<String>();
        if(uploadList.size()!=queryList.size()){
            errorList.add("学生人数不正确，请检查你提交的文件");
        }else{
            for(int i=0;i<uploadList.size();i++){
                if(!stuIdList.contains(uploadList.get(i).getStudentId()))
                    errorList.add("该学生不存在您的课程中:"+uploadList.get(i).getStudentId()+",不允许插入");
            }
        }
        if(errorList.size()==0){
            for(int i=0;i<uploadList.size();i++){
                selectCourseDAO.updateGrade(uploadList.get(i).getStudentId(),openId,uploadList.get(i).getGrade());
            }
            errorList.add("OK");
            return errorList;
        }else {
            return errorList;
        }
    }

}
