package com.crtvu.service.implementation;

import com.crtvu.dao.InfoDAO;
import com.crtvu.dto.Manager.ErrorJson;

import com.crtvu.dto.ReadInfoExcel;
import com.crtvu.entity.InfoEntity;
import com.crtvu.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;


@Service
public class InfoServiceImpl implements InfoService {

    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String salt="1>#@42!dw1E?#sffffffsF@F#$4!@#2";

    @Autowired
    private InfoDAO infoDAO;
    private static final int pageNumber = 10;

    public List<InfoEntity> getInfoList (int page, String infoProperty) {
        if(page<=0||page>getPageCount(infoProperty)) {
            return null;
        }
        return infoDAO.selectInfoByLimit(infoProperty,(page-1)*pageNumber,pageNumber);
    }

    public int getPageCount(String infoProperty) {
        int count = infoDAO.countAllInfo(infoProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    public InfoEntity getInfo(int id){
        return infoDAO.selectInfo(id);
    }

    public Result insertInfo(InfoEntity info) {
        if (info.getId()<0) {
            return Result.ID_LENGTH_FAIL;
        }
        try {
            Date ti = new Date();
            Timestamp time = new Timestamp(ti.getTime());
           infoDAO.insertInfo(info.getTitle(),info.getContext(),time,info.getSender(),info.getStudentId());
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result deleteInfo(int id){
        return infoDAO.deleteInfo(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    public Result updateInfo(int id, String title, String context, String sender, int studentId){
        if (id <= 0 ) {
            return Result.ID_LENGTH_FAIL;
        }
        int result;
        try {
            Date tim = new Date();
            Timestamp timeu = new Timestamp(tim.getTime());
            result = infoDAO.updateInfo(id,title,context,timeu,sender,studentId);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }


    public Map<String, Object> batchImport(String name,MultipartFile file){
        boolean b = false;
        Map<String, Object> modelMap = new HashMap<>();
        //创建处理EXCEL
        //解析excel，获取学生信息集合。
        ErrorJson errorEntity = new ErrorJson();
        ReadInfoExcel readInfoExcel = new ReadInfoExcel();
        modelMap = readInfoExcel.getExcelInfo(name ,file);
        List<InfoEntity> infoList = (List<InfoEntity>) modelMap.get("result");
        List<InfoEntity> removeList = new ArrayList();
        List<ErrorJson> errorList = (List<ErrorJson>) modelMap.get("error");
        if(infoList != null){
            //迭代添加学生信息（注：实际上这里也可以直接将studentList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for(InfoEntity info:infoList){
                if(this.getInfo(info.getId())==null){
                    if(info.getId()< 0) {
                        errorEntity = new ErrorJson();
                        errorEntity.setId(info.getId());
                        errorEntity.setErrors("id号不能为负");
                    }else {
                        this.insertInfo(info);
                    }
                }
                else{
                    removeList.add(info);
                    errorEntity = new ErrorJson();
                    errorEntity.setId(info.getId());
                    errorEntity.setErrors("id号已存在");
                    errorList.add(errorEntity);
                }
            }
        }

        if(removeList.size()>0)
        {
            infoList.removeAll(removeList);
        }
        modelMap.put("result",infoList);
        modelMap.put("error",errorList);
        System.out.println(infoList);
        return modelMap;
    }

    public List<InfoEntity> getRecentInfo(int studentId){
        return infoDAO.selectRecentInfoByStudentId(studentId);
    }

}
