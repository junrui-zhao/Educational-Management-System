package com.crtvu.web;

import com.crtvu.dto.Manager.DeleteJson;
import com.crtvu.entity.AdminEntity;
import com.crtvu.service.AdminService;
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
@RequestMapping("admin/admin")// url:模块/资源/{id}/细分
public class AdminTableController {

    //日志记录
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/admin/list/1";
    }

    // 管理员列表展示首页
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String listAll(Model model, @PathVariable("page") int page,
                          @RequestParam(value="keyword",defaultValue = "",required = false) String keyword) {
        try{
            //keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            List<AdminEntity> list = adminService.getAdminList(page,keyword);
            int pages = adminService.getPageCount(keyword);
            System.out.println(list);
            model.addAttribute("list", list);
            model.addAttribute("TotalPages", pages);
            model.addAttribute("keyword", keyword);
        }catch(Exception e){

        }
        return "admin/admin/list";
    }

    //编辑页跳转
    @RequestMapping(value = "/edit/{adminId}", method = RequestMethod.GET)
    public String edit(@PathVariable("adminId") int adminId, Model model) {
        AdminEntity adminEntity = adminService.getAdmin(adminId);
        model.addAttribute("adminEntity", adminEntity);
        return "admin/admin/edit";
    }

    //修改管理员密码
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AdminService.Result updatePassword(@RequestBody AdminEntity adminEntity) {
        return adminService.updateAdminPassword(adminEntity.getAdminId(), adminEntity.getPassword());
    }

    //根据ID删除管理员
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AdminService.Result delete(@RequestBody DeleteJson deletejson) {
        return adminService.deleteAdmin(deletejson.getId());
    }

    //增添管理员
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AdminService.Result insert(@RequestBody AdminEntity adminEntity) {
        adminEntity.setPassword("00000000");
        return adminService.insertAdmin(adminEntity);
    }

    //跳转至增添管理员页面
    @RequestMapping(value = "/insertPage" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/admin/insert";
    }

    //检查ID是否重复
    @RequestMapping(value = "/checkid" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AdminEntity checkId(@RequestBody AdminEntity adminEntity){
        return adminService.getAdmin(adminEntity.getAdminId());
    }
}
