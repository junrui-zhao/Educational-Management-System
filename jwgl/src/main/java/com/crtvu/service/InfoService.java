package com.crtvu.service;

/**
 * Created by yangming on 2017/4/27/0027.
 */
import java.sql.Timestamp;
import java.util.*;

import com.crtvu.entity.InfoEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 站在使用者的角度设计接口
 * 方法定义粒度，参数，返回类型，类型
 */
public interface InfoService {

    enum Result{
        SUCCESS,//成功
        STUDENTID_FAIL,//学号
        SENDER_FAIL,//发送者错误
        HASREAD_FAIL,//状态
        ID_FAIL,//id号重复
        ID_LENGTH_FAIL//ID长度不符合规则
    }

    /**
     * 对一组通知信息进行分组，并翻到第page页。
     * @param page
     * @return
     */
    List<InfoEntity> getInfoList(int page, String infoProperty);

    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String infoProperty);
    /**
     * 找到指定id的t通知
     * @param id
     * @return
     */
     InfoEntity getInfo(int id);
    /**
     * 添加新同学
     * @param info
     */
    Result insertInfo(InfoEntity info);

    /**
     * 删除某一ID的同学
     * @param id
     */
    Result deleteInfo(int id);

    /**
     * 修改通知
     * @param studentId
     * @param id
     * @param title
     * @param context
     * @param time
     * @param sender
     * @return
     */
    Result updateInfo(int id, String title, String context, String sender, int studentId);


    /**
     * 批量添加
     * @param name
     * @param file
     * @return
     */
    Map<String, Object> batchImport(String name, MultipartFile file);

    /**
     *
     * @param studentId
     * @return
     */
    List<InfoEntity> getRecentInfo(int studentId);
}