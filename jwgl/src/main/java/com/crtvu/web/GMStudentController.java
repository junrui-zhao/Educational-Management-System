package com.crtvu.web;

/**
 * Created by jwgl on 2017/4/3.
 */
import com.crtvu.dto.StudentCheckSubjectItem;
import com.crtvu.service.GMStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
@SessionAttributes("studentName")
public class GMStudentController {
    private GMStudentService GMStudentService;
    @Autowired
    public GMStudentController(GMStudentService GMStudentService) {
        this.GMStudentService = GMStudentService;
    }

    //查看已确认课题列表
    @RequestMapping(value = "/{studentId}/CheckConfirmedSubject")
    public String CheckConfirmedSubject(@PathVariable int studentId, Model model,
                                   HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("studentId",studentId);
        model.addAttribute("confirmedSubject", GMStudentService.checkConfirmedSubject(studentId));
        req.getSession().setAttribute("subjectStatus",2);
        String studentName = GMStudentService.queryStudent(studentId).getStudentName();
        model.addAttribute("studentName",studentName);
        return "StudentPage/CheckConfirmedSubject";
    }

    //查看未确认课题列表
    @RequestMapping(value= "/{studentId}/CheckNotConfirmedSubject")
    public String CheckNotConfirmedSubject(@PathVariable int studentId,Model model,
                                           HttpServletRequest req, HttpServletResponse resp){
        List<StudentCheckSubjectItem> studentCheckSubjectItems= GMStudentService.checkNotConfirmedSubject(studentId);
        model.addAttribute("notConfirmedSubject",studentCheckSubjectItems);
        req.getSession().setAttribute("studentId",studentId);
        req.getSession().setAttribute("subjectStatus",0);
        return "StudentPage/CheckNotConfirmedSubject";
    }

    //查看未确认的课题信息
    @RequestMapping(value ="/CheckSubjectInfo/{subjectId}")
    public String CheckSubjectInfo(@PathVariable int subjectId,Model model,
                                   HttpServletRequest req, HttpServletResponse resp){
        model.addAttribute("checkSubjectInfo", GMStudentService.checkSubjectInfo(subjectId));
        req.getSession().setAttribute("subjectId",subjectId);
        return "StudentPage/CheckSubjectInfo";
    }

    //查看已确认的课题信息并上传文档
    @RequestMapping(value ="/CheckSubjectDocu/{subjectId}")
    public String CheckSubjectDocu(@PathVariable int subjectId,Model model,
                                   HttpServletRequest req, HttpServletResponse resp){
        //int studentId = (Integer)req.getSession().getAttribute("studentId");
        model.addAttribute("checkSubjectDocu", GMStudentService.checkSubjectDocu(subjectId));
        req.getSession().setAttribute("subjectId",subjectId);
        return "StudentPage/CheckSubjectDocu";
    }

    //上传文档
    @RequestMapping(value="/uploadDocument")
    public String UpdateSubjectDocument(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        int sub = (Integer) req.getSession().getAttribute("subjectId");
        String doc = (String) req.getSession().getAttribute("document");
        int studentId = (Integer) req.getSession().getAttribute("studentId");
        int result = GMStudentService.updateSubjectDocument(sub,doc);
        resp.sendRedirect("/student/CheckSubjectDocu/"+sub);
        return null;
   }

   //申请课题
    @RequestMapping(value = "/insertSelectSubject/{subjectId}")
    @ResponseBody
    public int insertSelectSubject(@PathVariable int subjectId,
                                   HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentId = (Integer) req.getSession().getAttribute("studentId");

        int result = GMStudentService.insertSelectSubject(subjectId,studentId);
       if(result != 0){
           resp.sendRedirect("/student/"+studentId+"/CheckNotConfirmedSubject");
           return 1;
       }
       return 0;
   }

   //分页显示可申请的课题
    @RequestMapping(value = "/{studentId}/list/{page}")
    public String list(@PathVariable int studentId,
                       @PathVariable int page,
                       @RequestParam(value="keyword",defaultValue="", required=false)String keyword,
                       Model model,HttpServletRequest req, HttpServletResponse resp){
        int number=10;
        req.getSession().setAttribute("studentId",studentId);
        req.getSession().setAttribute("subjectStatus",3);
        List<StudentCheckSubjectItem> allList = new ArrayList<StudentCheckSubjectItem>();
        if (keyword == null){
            allList = GMStudentService.checkNotAppliedSubject(studentId);
        } else {
            allList = GMStudentService.keyNotAppliedSubject(studentId,keyword);
        }
        List<StudentCheckSubjectItem> pageList;
        int pages;
        if (allList.size() == 0){
            pages = 1;
            pageList = null;
        } else {
            pages= (int) Math.ceil((double) allList.size()/number);
            if (pages == 1){
                pageList = allList;
            } else {
                if (page == pages){
                    pageList = allList.subList((page-1)*number,allList.size()%number+(page-1)*number);
                } else {
                    pageList = allList.subList((page-1)*number,page*number);
                }
            }
        }

        System.out.println("**************************"+pages);
        model.addAttribute("list",pageList);
        model.addAttribute("pages",pages);
        return "StudentPage/list";
    }


   //撤回申请
    @RequestMapping(value = "/DeleteApplication/{subjectId}")
    @ResponseBody
    public String DeleteApplication(HttpServletRequest req, HttpServletResponse resp,@PathVariable int subjectId) throws IOException {
        int studentId = (Integer) req.getSession().getAttribute("studentId");
        //int subjectId = (Integer) req.getSession().getAttribute("subjectId");
        int result = GMStudentService.deleteApplication(studentId,subjectId);
        if(result == 1){
            resp.sendRedirect("/student/"+studentId+"/list/1");
            //return "redirect:/StudentPage/"+studentId+"/CheckNotAppliedSubject";
        }
        else {
            resp.sendRedirect("/student/"+studentId+"/CheckNotConfirmedSubject");
        }
        return null;
    }

   //返回主页
   @RequestMapping(value = "/{studentId}/goIndex")
    @ResponseBody
    public int goIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int studentId = (Integer) req.getSession().getAttribute("studentId");
        resp.sendRedirect("/student/"+studentId+"/CheckConfirmedSubject");
        return 0;
   }

    //文件上传
    @RequestMapping("/upload.do")
    public String upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
        for(MultipartFile file : myfiles){
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if(file.isEmpty()){
                System.out.println("文件未上传!");
            }
            else{
                //得到上传的文件名
                String fileName = file.getOriginalFilename();
                //得到服务器项目发布运行所在地址
                String path1 = request.getSession().getServletContext().getRealPath("file")+ File.separator;
                //  此处未使用UUID来生成唯一标识,用日期做为标识

                 String path2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
                request.getSession().setAttribute("document",path2);
                 String path = path1+path2;
                        //查看文件上传路径,方便查找
                System.out.println(path);
                //把文件上传至path的路径
                File localFile = new File(path);
                file.transferTo(localFile);
            }
        }
        return "redirect:/student/uploadDocument";
    }

}
