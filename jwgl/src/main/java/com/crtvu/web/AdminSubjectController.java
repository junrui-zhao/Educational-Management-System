package com.crtvu.web;

import com.crtvu.dto.StudentCheckSubjectItem;
import com.crtvu.dto.TeacherCheckDocumentItem;
import com.crtvu.service.GMStudentService;
import com.crtvu.service.GMTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/subject")
public class AdminSubjectController{

    @Autowired
    private GMStudentService gmStudentService;
    @Autowired
    private GMTeacherService gmTeacherService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/subject/list/1";
    }

    //分页显示可申请的课题
    @RequestMapping(value = "/list/{page}")
    public String list(@PathVariable int page,
                       @RequestParam(value="keyword",defaultValue="", required=false)String keyword,
                       Model model, HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        int number=10;
        System.out.println(keyword);
        List<StudentCheckSubjectItem> allList = new ArrayList<StudentCheckSubjectItem>();
        if (keyword == null){
            allList = gmStudentService.queryAllSubject();
        }
        else {
            allList = gmStudentService.keywordSubject(keyword);
        }
        //List<StudentCheckSubjectItem> allList = gmStudentService.queryAllSubject();
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

        //System.out.println("**************************"+pages);
        model.addAttribute("list",pageList);
        model.addAttribute("pages",pages);
        return "admin/subject/list";
    }

    //查看未确认的课题信息
    @RequestMapping(value ="/{subjectId}")
    public String CheckSubjectInfo(@PathVariable int subjectId,Model model,
                                   HttpServletRequest req, HttpServletResponse resp){
        model.addAttribute("checkSubjectInfo", gmStudentService.checkSubjectInfo(subjectId));
        req.getSession().setAttribute("subjectId",subjectId);
        return "admin/subject/subjectInfo";
    }

    //查看已确认课题信息及文档
    @RequestMapping(value = "/{subjectId}/1")
    public String getSubjectDocument(@PathVariable int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req){
        //System.out.println("hello");
        //System.out.println(gmTeacherService.getSubjectDocument(1));
        TeacherCheckDocumentItem subjectItem = gmTeacherService.getSubjectDocument(subjectId);
        model.addAttribute("getSubjectDocument",subjectItem);
        req.getSession().setAttribute("document",subjectItem.getDocument());
        return "/admin/subject/subjectInfo1";
    }

    //删除课题
    @RequestMapping(value = "/delete/{subjectId}")
    public String delete(@PathVariable("subjectId") int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req) throws Exception {
        System.out.println(subjectId);
        int result = gmTeacherService.deleteSubject(subjectId);
        //resp.sendRedirect("redirect:/admin/subject/list");
        return "redirect:/admin/subject/list/1";
    }

    //取消课题
    @RequestMapping(value = "/cancel/{subjectId}")
    public String cancel(@PathVariable("subjectId") int subjectId,Model model,HttpServletResponse resp,HttpServletRequest req) throws Exception {
        System.out.println(subjectId);
        int result = gmTeacherService.deleteConfirmSubject(subjectId);
        //resp.sendRedirect("redirect:/admin/subject/list");
        return "redirect:/admin/subject/list/1";
    }

}

