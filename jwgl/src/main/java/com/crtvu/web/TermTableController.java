package com.crtvu.web;

import com.crtvu.dto.Manager.DeleteJson;
import com.crtvu.entity.TermYearEntity;
import com.crtvu.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gao27024037 on 2017/4/30.
 */
@Controller //@Service @Component
@RequestMapping("admin/term")// url:模块/资源/{id}/细分
public class TermTableController {

    //日志记录
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TermService termService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/term/list/1";
    }

    // 学期列表展示首页
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String listAll(Model model, @PathVariable("page") int page,
                          @RequestParam(value="keyword",defaultValue = "",required = false) String keyword) {
        try{
            //keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            List<TermYearEntity> list = termService.getTermList(page,keyword);
            int pages = termService.getPageCount(keyword);
            System.out.println("termlist +"+list);
            System.out.println("123");
            model.addAttribute("list", list);
            model.addAttribute("TotalPages", pages);
            model.addAttribute("keyword", keyword);
            System.out.println("456");
        }catch(Exception e){

        }
        return "admin/term/list";
    }

    //编辑页跳转
    @RequestMapping(value = "/edit/{termId}", method = RequestMethod.GET)
    public String edit(@PathVariable("termId") int termId, Model model) {
        TermYearEntity termYearEntity = termService.getTerm(termId);
        model.addAttribute("termEntity", termYearEntity);
        return "admin/term/edit";
    }

    //修改学期信息
    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TermService.Result updateInformation(@RequestBody TermYearEntity termYearEntity) {
        return termService.updateTerm(termYearEntity.getSchoolYear(), termYearEntity.getTerm(),
                termYearEntity.getBeginTime(), termYearEntity.getOverTime(), termYearEntity.getId());
    }

    //根据ID删除学期
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TermService.Result delete(@RequestBody DeleteJson deletejson) {
        return termService.deleteTerm(deletejson.getId());
    }

    //增添学期
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TermService.Result insert(@RequestBody TermYearEntity termYearEntity) {
        return termService.insertTerm(termYearEntity);
    }

    //跳转至增添学期页面
    @RequestMapping(value = "/insertPage" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/term/insert";
    }

}
