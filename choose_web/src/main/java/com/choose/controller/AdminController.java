package com.choose.controller;

import com.choose.entity.Admin;
import com.choose.entity.Course;
import com.choose.entity.Teacher;
import com.choose.info.AdminInfo;
import com.choose.service.AdminService;
import com.choose.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourseService courseService;
    //管理员添加课程安排
    @RequestMapping("/add")
    public String add(Admin admin, Model model,HttpServletRequest request)
    {
        //模拟注入老师，实际上是从session中取
        Teacher teacher1=new Teacher();
        teacher1.setId(1);
        request.getSession().setAttribute("login",teacher1);
        request.getSession().setAttribute("type",1);
        Teacher teacher= (Teacher) request.getSession().getAttribute("login");
        //设置教师id
        admin.setTeacher(teacher);
        int add = adminService.add(admin);
        if(add==-1)
        {
            //时间冲突，告知管理员，重新插入
            List<Course> courses = courseService.getAll();
            model.addAttribute("courses",courses);
            model.addAttribute("message","时间冲突，请重新插入");
            return "createCourse";
        }else{
            request.setAttribute("message","课程插入成功");
            return "forward:/admin/detailByDay/"+admin.getDay();
        }
    }

    //预选择某门课
    @RequestMapping("/preChoose")
    public String preChoose()
    {
        return "redirect:/detail/";
    }
    //进入某一天课详情界面
    @RequestMapping("/detailByDay/{day}")
    public String detail(@PathVariable("day")Integer day, Model model)
    {
        List<AdminInfo> infos = adminService.getByDay(day);
        model.addAttribute("infos",infos);
        model.addAttribute("day",day);
        return "AllCourse";
    }
    //跳到添加新课程界面
    @RequestMapping("/addPage")
    public String addCourse(Model model)
    {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses",courses);
        return "createCourse";
    }

    //管理员删除某次课
    @RequestMapping("/removeCourse/{adminId}")
    public String remove(@PathVariable("adminId")Integer adminId,Model model)
    {
        //表中有外键，如果删除失败会报500
        //如果教师想要删除课程，需告诉学生让学生先删除
        adminService.removeByPrimaryKey(adminId);
        return "redirect:/admin/detailByDay/1";
    }

    //管理员查看自己所安排的课程,此处应该有删除自己所选某门课的选项
    @RequestMapping("/teacherCourses")
    public String teacherCourse(HttpServletRequest request,Model model)
    {
        //Teacher teacher = (Teacher) request.getSession().getAttribute("login");
        List<AdminInfo> teacherCourses = adminService.getByTeacherId(2);
        model.addAttribute("teacherCourses",teacherCourses);
        return "";
    }
}
