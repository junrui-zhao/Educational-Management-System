package com.crtvu.service.implementation;

import com.crtvu.dao.ClassroomDAO;
import com.crtvu.dto.ErrorCJson;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.ReadExcel;
import com.crtvu.dto.ReadRExcel;
import com.crtvu.entity.ClassroomEntity;
import com.crtvu.entity.StudentEntity;
import com.crtvu.service.ClassroomService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danagi on 2017/4/6.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomDAO classroomDAO;
    private static final int pageNumber = 10;
    public Result insertClassroom(String classroom, int peopleNum) {
        if (peopleNum <= 0 || peopleNum >= 1000) {
            return Result.NUM_FAIL;
        }
        try {
            classroomDAO.insertClassroom(classroom,peopleNum);
        }
        catch (Exception e){
            return Result.ID_FAIL;
        }
        return Result.SUCCESS;
    }

    public Result updateClassroom(String classroom, String newRoomName, int peopleNum) {
        if (peopleNum <= 0 || peopleNum >= 1000) {
            return Result.NUM_FAIL;
        }
        int result;
        try {
            result=classroomDAO.updateClassroom(classroom, newRoomName, peopleNum);
        }
        catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

    public Result deleteClassroom(String classroom) {
        return classroomDAO.deleteClassroom(classroom) > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

    public ClassroomEntity getClassroom(String classroom) {
        return classroomDAO.selectClassroom(classroom);
    }

    public List<ClassroomEntity> getClassroomList(int page,String classroomProperty) {
        if(page<=0||page>getPageCount(classroomProperty)) {
            return null;
        }
        return classroomDAO.selectClassroomByLimit(classroomProperty,(page-1)*pageNumber,pageNumber);
    }

    public int getPageCount(String classroomProperty) {
        int count = classroomDAO.countAllClassroom(classroomProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    @Override
    public List<ClassroomEntity> getAllClassroom() {
        return classroomDAO.selectAllClassroom();
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
        for(int i = 0; i < list.size(); i++) {
            String error = "";
            if (list.get(i).size() < 3) {
                for (int j = list.get(i).size(); j <= 3; j++)
                    list.get(i).add(j,"");
                error += "数据不能为空 ";
            } else if (this.getClassroom(list.get(i).get(0).toString()) != null) {
                error += "该工号已存在 ";
            }
            if (error.equals("")) {
                Result flag = this.insertClassroom(list.get(i).get(0).toString(),
                        Integer.valueOf(list.get(i).get(1).toString()));

                if (flag == Result.ID_FAIL) {
                    list.get(i).add(3, "id已存在");
                } else{
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
    public Map<String, Object> batchImport(String name,MultipartFile file){
        boolean b = false;
        Map<String, Object> modelMap = new HashMap<>();
        //创建处理EXCEL
        ReadRExcel readExcel=new ReadRExcel();
        //解析excel，获取学生信息集合。
        ErrorCJson errorEntity = new ErrorCJson();
        modelMap = readExcel.getExcelInfo(name ,file);
        List<ClassroomEntity> classroomList = (List<ClassroomEntity>) modelMap.get("result");
        List<ClassroomEntity> removeList = new ArrayList();
        List<ErrorCJson> errorList = (List<ErrorCJson>) modelMap.get("error");
        if(classroomList != null){
            //迭代添加学生信息（注：实际上这里也可以直接将studentList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for(ClassroomEntity classroomEntity:classroomList){
                if(this.getClassroom(classroomEntity.getClassroom())==null){

                        this.insertClassroom(classroomEntity.getClassroom(),classroomEntity.getPeopleNum());

                }
                else{
                    removeList.add(classroomEntity);
                    errorEntity = new ErrorCJson();
                    errorEntity.setCourseId(classroomEntity.getClassroom());
                    errorEntity.setErrors("学号已存在");
                    errorList.add(errorEntity);
                }
            }
        }

        if(removeList.size()>0)
        {
            classroomList.removeAll(removeList);
        }
        modelMap.put("result",classroomList);
        modelMap.put("error",errorList);
        System.out.println(classroomList);
        return modelMap;
    }
}