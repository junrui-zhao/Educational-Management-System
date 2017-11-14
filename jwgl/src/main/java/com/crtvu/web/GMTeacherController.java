package com.crtvu.web;

import com.crtvu.dto.ReleasedSubjectListItem;
import com.crtvu.dto.TeacherCheckDocumentItem;
import com.crtvu.service.GMTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by buctc on 2017/4/4.
 */
@Controller
@RequestMapping(value = "/teacher")
@SessionAttributes("teacherName")
public class GMTeacherController {
    private GMTeacherService GMTeacherService;

    @Autowired
    public GMTeacherController(GMTeacherService GMTeacherService) {
        this.GMTeacherService = GMTeacherService;
    }

    //查询该教师发布的课题列表
    @RequestMapping(value = "/{teacherId}/subjects")
    public String getAllReleasedSubject(@PathVariable("teacherId") int teacherId, Model model,HttpServletRequest req){
        List<ReleasedSubjectListItem> releasedSubjectListItems= GMTeacherService.getAllReleasedSubject(teacherId);

        String teacherName = GMTeacherService.queryTeacher(teacherId).getTeacherName();
        //String teacherName = teacher.getName();
        model.addAttribute("teacherName",teacherName);
        model.addAttribute("releasedSubjectListItems",releasedSubjectListItems);
        return "/TeacherPage/getAllReleasedSubject";
    }

    //添加课题
    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    @ResponseBody
    public String addSubject(@RequestParam("subjectName") String subjectName,
                             @RequestParam("subjectRequirement") String rm,
                             HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //resp.setCharacterEncoding("utf-8");
        int teacherId = 0;
        try {
            teacherId = Integer.parseInt((String) req.getSession().getAttribute("userid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //.getBytes("ISO-8859-1"),"GBK"
        //String subjectName = new String(req.getParameter("subjectName").getBytes("ISO-8859-1"));
        //String rm = new String(req.getParameter("subjectRequirement").getBytes("ISO-8859-1"));
        String requirement = '\n' + rm;
        //System.out.println(teacherId+subjectName+requirement);
        int result = GMTeacherService.insertSubject(teacherId,subjectName,requirement);
        //sendRedirect("/TeacherPage/getAllReleasedSubject");
        if(result == 0) {
            return "/TeacherPage/insertSubject";
        } else {
            resp.sendRedirect("/teacher/"+teacherId+"/subjects");
            return null;
        }
    }

    //添加课题
    @RequestMapping(value = "/insertSubject.action")
    public String insert() throws Exception {
        return "/TeacherPage/insertSubject";
    }

    //修改课题
    @RequestMapping(value = "/{teacherId}/update/{subjectId}", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable("teacherId")int teacherId,
                         @PathVariable("subjectId") int subjectId,
                         @RequestParam("subjectName") String subjectName,
                         @RequestParam("subjectRequirement") String rm,
                         HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //String subjectName = new String(req.getParameter("subjectName"));//.getBytes("ISO-8859-1")
        //String rm = new String(req.getParameter("subjectRequirement"));//.getBytes("ISO-8859-1"))
        String requirement = '\n' + rm;
        int result = GMTeacherService.updateSubject(subjectId,subjectName,requirement);
        resp.sendRedirect("/teacher/"+teacherId+"/getSubjectInfo/"+subjectId);
        return null;
    }

    @RequestMapping(value = "/{teacherId}/updateSubject/{subjectId}")
    public String updateSubject(@PathVariable("teacherId")int teacherId, @PathVariable("subjectId") int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req){
        model.addAttribute("getSubjectInfo", GMTeacherService.getSubjectInfo(subjectId));
        //req.setAttribute("subjectId",subjectId);
        req.getSession().setAttribute("subjectId",subjectId);
        return "/TeacherPage/updateSubject";
    }

    //删除课题
    @RequestMapping(value = "/{teacherId}/deleteSubject/{subjectId}")
    public String delete(@PathVariable("teacherId") int teacherId, @PathVariable("subjectId") int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req) throws Exception {
        int result = GMTeacherService.deleteSubject(subjectId);
        resp.sendRedirect("/teacher/"+teacherId+"/subjects");
        return null;
    }

    //返回首页
    @RequestMapping(value = "/goIndex")
    public String goIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int teacherId = (Integer) req.getSession().getAttribute("teacherId");
        resp.sendRedirect("/teacher/"+teacherId);
        return null;
    }

    //查看未确认课题信息及选课题的同学
    @RequestMapping(value = "/{teacherId}/getSubjectInfo/{subjectId}")
    public String getSubjectInfo(@PathVariable("teacherId")int teacherId, @PathVariable("subjectId") int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req){
        model.addAttribute("getSubjectInfo", GMTeacherService.getSubjectInfo(subjectId));
        req.getSession().setAttribute("subjectId",subjectId);
        return "/TeacherPage/getSubjectInfo";
    }

    //查看已确认课题信息及文档
    @RequestMapping(value = "/{studentId}/getSubjectDocument/{subjectId}")
    public String getSubjectDocument(@PathVariable int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req){
        TeacherCheckDocumentItem subjectItem = GMTeacherService.getSubjectDocument(subjectId);
        model.addAttribute("getSubjectDocument",subjectItem);
        req.getSession().setAttribute("document",subjectItem.getDocument());
        return "/TeacherPage/getSubjectDocument";
    }

    //确定学生
    @RequestMapping(value = "/{teacherId}/confirm/{studentId}")
    @ResponseBody
    public int confirmStudent(@PathVariable int studentId,HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //int teacherId = (Integer) req.getSession().getAttribute("teacherId");
        int teacherId = Integer.parseInt((String) req.getSession().getAttribute("userid"));
        int subjectId = (Integer) req.getSession().getAttribute("subjectId");
        int result = GMTeacherService.confirmStudent(subjectId,studentId);
        resp.sendRedirect("/teacher/"+teacherId);
        return 0;
    }

    //下载
    @RequestMapping("/{teacherId}/download")
    public String download(@PathVariable("teacherId")int teacherId, String fileName, HttpServletRequest request,
                           HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //fileName = (String) request.getSession().getAttribute("document");
        response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

        try {
            String path = request.getSession().getServletContext().getRealPath("file")+ File.separator;
            InputStream inputStream = new FileInputStream(new File(path+ fileName));
            System.out.println(path + fileName);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }



}
