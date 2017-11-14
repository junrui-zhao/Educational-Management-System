package com.crtvu.web;

/**
 * Created by lcf12 on 2017/3/17.
 */
import com.crtvu.dto.Manager.DeleteJson;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.dto.Manager.PasswordJson;
import com.crtvu.dto.Manager.StudentJson;
import com.crtvu.dto.downloadFile;
import com.crtvu.entity.StudentEntity;
import com.crtvu.service.StudentService;
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

@Controller
@RequestMapping("/admin/student")
public class StudentTableController {

    @Autowired
    private StudentService StudentService;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return  "admin/student/upload";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/admin/student/list/1";
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "searchKey",defaultValue = "") String studentProperty,
                       Model model,@PathVariable("page") int page){
        try{
            //studentProperty = new String(studentProperty.getBytes("ISO-8859-1"), "UTF-8");
            List<StudentEntity> list= StudentService.getStudentList(page,studentProperty);
            int pages = StudentService.getPageCount(studentProperty);
            model.addAttribute("list",list);
            model.addAttribute("pages",pages);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "admin/student/list";
    }

    @RequestMapping(value = "/edit/{studentId}",method = RequestMethod.GET)
    public String edit(@PathVariable("studentId") int id, Model model){

        try{
            StudentEntity student= StudentService.getStudent(id);
            if (student == null){
                return "forward:/admin/student/list/1";
            }
            model.addAttribute("student",student);
            return "admin/student/edit";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "admin/student/index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StudentService.Result delete(@RequestBody DeleteJson deletejson)
    {
        return StudentService.deleteStudent(deletejson.getId());
    }

    @RequestMapping(value = "/insertInfo" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "admin/student/insert";
    }

    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StudentService.Result insert(@RequestBody StudentJson student ){

        StudentEntity StudentEntity = new StudentEntity(student.getId(),student.getName(),student.getClassName(),student.getMajor(),student.getPassword());
        return StudentService.insertStudent(StudentEntity);

    }

    @RequestMapping(value = "/updateStudent" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StudentService.Result updateStudent(@RequestBody StudentJson student){
        return StudentService.updateStudent(student.getId(),student.getName(),student.getClassName(),student.getMajor());
    }

    @RequestMapping(value = "/changePassword" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public StudentService.Result changePassword(@RequestBody PasswordJson PasswordJson){

        return StudentService.updateStudentPassword(PasswordJson.getId(), PasswordJson.getNewPassword());

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
        modelMap = StudentService.batchImport(name,file);
        if(modelMap != null){
            String Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            String Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        List<StudentEntity> studentList = (List<StudentEntity>)modelMap.get("result");
        List<ErrorJson> errorList = (List<ErrorJson>)modelMap.get("error");
        model.addAttribute("success",studentList);
        model.addAttribute("error",errorList);
        return "/admin/student/upload";
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "SampleStudentExcel.xls";
        downloadFile downloadFile = new downloadFile();
        downloadFile.downloadFile(request,response,fileName);
        return null;
    }

}
