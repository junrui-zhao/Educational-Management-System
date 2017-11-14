package com.crtvu.web;

import com.crtvu.dto.EditClassroomJson;
import com.crtvu.dto.ErrorCJson;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.downloadFile;
import com.crtvu.entity.ClassroomEntity;
import com.crtvu.entity.StudentEntity;
import com.crtvu.service.ClassroomService;
import com.crtvu.service.implementation.ClassroomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danagi on 2017/4/6.
 */
@Controller
@RequestMapping("/admin/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomServiceImpl classroomService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/classroom/list/1";
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "searchKey",defaultValue = "") String classroomProperty,
                       Model model,@PathVariable("page")int page){
        List<ClassroomEntity> classroomEntities = classroomService.getClassroomList(page,classroomProperty);
        if(classroomEntities == null)return "redirect:/admin/classroom/list/1";
        model.addAttribute("list",classroomEntities);
        model.addAttribute("pageCount",classroomService.getPageCount(classroomProperty));
        return "/admin/classroom/list";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ClassroomService.Result delete(@RequestBody ClassroomEntity classroomEntity){
        return classroomService.deleteClassroom(classroomEntity.getClassroom());
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String insert(){
        return "/admin/classroom/insert";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ClassroomService.Result insert(@RequestBody ClassroomEntity classroomEntity){
        return classroomService.insertClassroom(classroomEntity.getClassroom(),classroomEntity.getPeopleNum());
    }

    @RequestMapping(value = "/edit/{classroom}",method = RequestMethod.GET)
    public String edit(Model model,@PathVariable("classroom")String classroom){
        System.out.println(classroom);
        ClassroomEntity classroomEntity = classroomService.getClassroom(classroom);
        if(classroomEntity==null)return "redirect:/admin/classroom/list/1";
        model.addAttribute("classroom",classroomEntity);
        return "/admin/classroom/edit";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ClassroomService.Result edit(@RequestBody EditClassroomJson editClassroomJson){
        return classroomService.updateClassroom(editClassroomJson.getClassroom(),editClassroomJson.getNewRoomName(),editClassroomJson.getPeopleNum());
    }
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response){
        String fileName = "SampleClassroomExcel.xls";

        downloadFile downloadFile = new downloadFile();
        downloadFile.downloadFile(request,response,fileName);
        return null;
    }
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return  "admin/classroom/upload";
    }
    //上传页面
    @RequestMapping(value="/batchimport")
    public  String batchimport(@RequestParam("file") MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response , Model model) throws IOException {
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        Map<String, Object> modelMap = new HashMap<>();
        //批量导入。参数：文件名，文件。
        modelMap = classroomService.batchImport(name,file);
        if(modelMap != null){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        List<ClassroomEntity> classroomList = (List<ClassroomEntity>)modelMap.get("result");
        List<ErrorCJson> errorList = (List<ErrorCJson>)modelMap.get("error");
        model.addAttribute("success",classroomList);
        model.addAttribute("error",errorList);
        return "/admin/classroom/upload";
    }


}
