package com.crtvu.web;

import com.crtvu.dto.Manager.DeleteJson;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.downloadFile;
import com.crtvu.entity.InfoEntity;
import com.crtvu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gao27024037 on 2017/4/30.
 */
@Controller
@RequestMapping("/admin/info")
public class InfoTableController {

    @Autowired
    private com.crtvu.service.InfoService InfoService;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return  "admin/info/upload";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/info/list/1";
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "searchKey",defaultValue = "") String infoProperty,
                       Model model,@PathVariable("page") int page){
        try{
            //infoProperty = new String(infoProperty.getBytes("ISO-8859-1"), "UTF-8");
            List<InfoEntity> list= InfoService.getInfoList(page,infoProperty);
            int pages = InfoService.getPageCount(infoProperty);
            model.addAttribute("list",list);
            model.addAttribute("page",page);
            model.addAttribute("pages",pages);
            model.addAttribute("searchKey",infoProperty);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "admin/info/list";
    }

    @RequestMapping(value = "/edit/{infoId}",method = RequestMethod.GET)
    public String edit(@PathVariable("infoId") int id, Model model){

        try{
            InfoEntity info= InfoService.getInfo(id);
            if (info == null){
                return "forward:/admin/info/list/1";
            }
            model.addAttribute("info",info);
            return "admin/info/edit";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "admin/info/index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public InfoService.Result delete(@RequestBody DeleteJson deletejson)
    {
        return InfoService.deleteInfo(deletejson.getId());
    }

    @RequestMapping(value = "/insertInfo" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/info/insert";
    }

    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public InfoService.Result insert(@RequestBody InfoEntity info ){
        return InfoService.insertInfo(info);

    }

    @RequestMapping(value = "/updateInfo" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public InfoService.Result updateInfo(@RequestBody InfoEntity info){
        System.out.println("inforcontroller");
        return InfoService.updateInfo(info.getId(),info.getTitle(),info.getContext(),
                info.getSender(), info.getStudentId());
    }

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
        modelMap = InfoService.batchImport(name,file);
        if(modelMap != null){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        List<InfoEntity> infoList = (List<InfoEntity>)modelMap.get("result");
        List<ErrorJson> errorList = (List<ErrorJson>)modelMap.get("error");
        model.addAttribute("success",infoList);
        model.addAttribute("error",errorList);
        return "/admin/info/upload";
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "SampleInfoExcel.xls";
        downloadFile downloadFile = new downloadFile();
        downloadFile.downloadFile(request,response,fileName);
        return null;
    }
}
