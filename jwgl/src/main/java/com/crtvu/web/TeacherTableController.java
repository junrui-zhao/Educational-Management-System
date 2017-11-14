package com.crtvu.web;

import com.crtvu.dto.Manager.DeleteJson;
import com.crtvu.dto.downloadFile;
import com.crtvu.entity.TeacherEntity;
import com.crtvu.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by gao27024037 on 2017/3/25.
 */

@Controller //@Service @Component
@RequestMapping("admin/teacher")// url:模块/资源/{id}/细分
public class TeacherTableController {

    //日志记录
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/teacher/list/1";
    }

    // 教师列表展示首页
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String listAll(Model model, @PathVariable("page") int page,
                          @RequestParam(value="keyword",defaultValue = "",required = false) String keyword) {
       try{
           //keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
           //System.out.println(keyword);
           List<TeacherEntity> list = teacherService.getTeacherList(page,keyword);
           int pages = teacherService.getPageCount(keyword);
           System.out.println(list);
           model.addAttribute("list", list);
           model.addAttribute("TotalPages", pages);
           model.addAttribute("keyword", keyword);
       }catch(Exception e){

       }
        return "admin/teacher/list";
    }

    //编辑页跳转
    @RequestMapping(value = "/edit/{teacherId}", method = RequestMethod.GET)
    public String edit(@PathVariable("teacherId") int teacherId, Model model) {
        TeacherEntity teacherEntity = teacherService.getTeacher(teacherId);
        model.addAttribute("teacherEntity", teacherEntity);
        return "admin/teacher/edit";
    }

    //修改教师信息
    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TeacherService.Result updateInformation(@RequestBody TeacherEntity teacherEntity) {
        return teacherService.updateTeacher(teacherEntity.getTeacherId(), teacherEntity.getTeacherName(), teacherEntity.getTitle());

    }

    //修改教师密码
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TeacherService.Result updatePassword(@RequestBody TeacherEntity teacherEntity) {
        return teacherService.updateTeacherPassword(teacherEntity.getTeacherId(), teacherEntity.getPassword());
    }

    //根据ID删除教师
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TeacherService.Result delete(@RequestBody DeleteJson deletejson) {
        return teacherService.deleteTeacher(deletejson.getId());
    }

    //增添教师
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TeacherService.Result insert(@RequestBody TeacherEntity teacherEntity) {
        teacherEntity.setPassword("00000000");
        return teacherService.insertTeacher(teacherEntity);
    }

    //跳转至增添教师页面
    @RequestMapping(value = "/insertPage" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/teacher/insert";
    }

    //检查ID是否重复
    @RequestMapping(value = "/checkid" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TeacherEntity checkId(@RequestBody TeacherEntity teacherEntity){
        return teacherService.getTeacher(teacherEntity.getTeacherId());
    }


    //下载报表
    @RequestMapping(value = "/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        downloadFile  downloadFile = new downloadFile();
        String fileName = "SampleTeacherExcel.xls";
        downloadFile.downloadFile(request,response,fileName);
        return null;
    }
    //跳转至上传页面
    @RequestMapping("upload")
    public String upload() throws Exception {
        return "admin/teacher/upload";
    }

    //上传页面
    @RequestMapping(value="uploadexcle",method={RequestMethod.GET,RequestMethod.POST})
    public  String  uploadExcel(HttpServletRequest request, Model model) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in =null;
        List<List<Object>> list = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        list = teacherService.getListByExcel(in,file.getOriginalFilename());
        in.close();
        model.addAttribute("list",list);
        return "admin/teacher/upload";
    }

}
