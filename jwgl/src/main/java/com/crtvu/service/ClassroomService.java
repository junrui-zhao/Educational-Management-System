package com.crtvu.service;

import com.crtvu.entity.ClassroomEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by danagi on 2017/4/6.
 */
public interface ClassroomService {

    enum Result{
        SUCCESS,
        ID_FAIL,
        NUM_FAIL
    }

    /**
     *
     * @param classroom
     * @param peopleNum
     */
    Result insertClassroom(String classroom, int peopleNum);

    /**
     *
     * @param classroom
     * @param newRoomName
     * @param peopleNum
     */
    Result updateClassroom(String classroom, String newRoomName, int peopleNum);

    /**
     *
     * @param classroom
     */
    Result deleteClassroom(String classroom);

    /**
     *
     * @param classroom
     * @return
     */
    ClassroomEntity getClassroom(String classroom);

    /**
     *
     * @param page
     * @return
     */
    List<ClassroomEntity> getClassroomList(int page, String classroomProperty);

    /**
     *
     * @return
     */
    int getPageCount(String classroomProperty);

    List<ClassroomEntity> getAllClassroom();
    /**
     * 获取用户传来的报表文件并进行处理，添加教师
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception;
    /**
     * 批量添加
     * @param name
     * @param file
     * @return
     */
    Map<String, Object> batchImport(String name, MultipartFile file);
}
