package com.choose.controller;

import com.choose.entity.Admin;
import com.choose.entity.Course;
import com.choose.entity.Teacher;
import com.choose.info.AdminInfo;
import com.choose.service.AdminService;
import com.choose.service.CourseService;
import com.choose.service.TeacherService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    //管理员添加课程安排
    @RequestMapping("/add")
    public String add(Admin admin, Model model,HttpServletRequest request)
    {
        //模拟注入老师，实际上是从session中取
        Teacher teacher=(Teacher) request.getSession().getAttribute("login");
        //设置教师id
        admin.setTeacher(teacher);
        int add = adminService.add(admin);
        if(add==-1)
        {
            //时间冲突，告知管理员，重新插入
            List<Course> courses = courseService.getAll();
            model.addAttribute("message","时间冲突，请重新插入");
            model.addAttribute("url","/admin/addPage");
            return "errorPage";
        }else{

            model.addAttribute("message","添加成功，即将跳转到课程管理");
            model.addAttribute("url","/admin/detailByDay/"+admin.getDay());
            return "correctPage";
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
    @RequestMapping("/removeCourse/{adminId}/{day}")
    public String remove(@PathVariable("adminId")Integer adminId,@PathVariable("day")Integer day,Model model)
    {
        //表中有外键，如果删除失败会报500
        //如果教师想要删除课程，需告诉学生让学生先删除
       try {
           adminService.removeByPrimaryKey(adminId);
       }catch (Exception e)
       {
           model.addAttribute("message","此课程已经被预约，删除失败");
           model.addAttribute("url","/admin/detailByDay/"+day);
           return "errorPage";
       }
        //成功
        model.addAttribute("message","删除成功,即将跳转至课程管理页面");
        model.addAttribute("url","/admin/detailByDay/"+day);
        return "correctPage";
    }
    @RequestMapping("/removeMyCourse/{adminId}/{day}")
    public String removeMy(@PathVariable("adminId")Integer adminId,@PathVariable("day")Integer day,Model model)
    {
        //表中有外键，如果删除失败会报500
        //如果教师想要删除课程，需告诉学生让学生先删除
        try {
            adminService.removeByPrimaryKey(adminId);
        }catch (Exception e)
        {
            model.addAttribute("message","此课程已经被预约，删除失败");
            model.addAttribute("url","/admin/teacherSelfCourses?day="+day);
            return "errorPage";
        }
        //成功
        model.addAttribute("message","删除成功,即将跳转至课程管理页面");
        model.addAttribute("url","/admin/teacherSelfCourses?day="+day);
        return "correctPage";
    }
    //管理员查看自己所安排的课程,此处应该有删除自己所选某门课的选项
    @RequestMapping("/teacherCourses")
    public String teacherCourse(HttpServletRequest request, Model model)
    {
        //Teacher teacher = (Teacher) request.getSession().getAttribute("login");
        Integer techerId=((Teacher)request.getSession().getAttribute("login")).getId();
        List<AdminInfo> teacherCourses = adminService.getByTeacherId(techerId);

        model.addAttribute("admins",teacherCourses);
        return "studentChoosed";
    }
    @RequestMapping("/teacherSelfCourses")
    public String teacherSelfCourse(@RequestParam(value = "day",required = false) Integer day,HttpServletRequest request, Model model)
    {
        //Teacher teacher = (Teacher) request.getSession().getAttribute("login");
        if(day==null)
        {
            day=1;
        }
        Integer techerId=((Teacher)request.getSession().getAttribute("login")).getId();
        List<AdminInfo> teacherCourses = adminService.getByTeacherId(techerId);
        //把老师的信息注入
        model.addAttribute("infos",teacherCourses);
        model.addAttribute("day",day);
        return "teacherSelfCourses";
    }

    //添加某门课程
    @RequestMapping("/addCourse")
    public void addCourse(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //前台需传来该参数
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String courseName = request.getParameter("courseName");
        System.out.println(courseName);
        Course course=new Course();
        course.setName(courseName);
        int add = courseService.add(course);
            if(add==-1)
        {
           out.print("{\"success\":\"0\"}");//插入失败
        }else{
            out.print("{\"success\":\"1\"}");//插入成功
        }
        //返回到合适的界面
    }

    //教师注册
    @RequestMapping("/register")
    public String register(Teacher teacher, Model model) {
        if(teacher.getAccount()==null||teacher.getAccount().equals("")||teacher.getPassword()==null||teacher.getPassword().equals(""))
        {
            return "registerForTeacher";
        }
        String account = teacher.getAccount();
        Teacher teacher1 = teacherService.getByAccount(account);
        if (teacher1 != null) {
            //账户已经被注册
            model.addAttribute("msg", "该账号已被注册,换一个吧");
            model.addAttribute("user", teacher);
            //返回到教师注册界面并注入相应值
            return "registerForTeacher";
        } else {
            teacherService.add(teacher);
            //返回到登录界面并将该参数注入到登录表单里
            model.addAttribute("user", teacher);
            model.addAttribute("message","添加教师账户成功,即将调到教师登录界面");
            model.addAttribute("url","/admin/detailByDay/1");
            return "correctPage"; //添加成功  返回课程管理管理页面
        }
    }
    @RequestMapping("/registerPage")
    public  String registerPage()
    {
       return "registerForTeacher";
    }

    @RequestMapping("/manageCoursePage")
    public  String addCoursePage(Model model){//取出所有的课程
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses",courses);
        return "manageCourses";
    }
    @RequestMapping("/correctPage")

    public  String toCorrect(Model model,@RequestParam("message") String message,@RequestParam("url") String url){
        model.addAttribute("message",message);
        model.addAttribute("url",url);
        return "correctPage";
    }

    @RequestMapping("/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable("courseId") Integer courseId,Model model){

        try {
            courseService.removeByPrimaryKey(courseId);
        }catch (Exception e)
        {
            model.addAttribute("message","删除失败，该课程已经开课");
            model.addAttribute("url","/admin/manageCoursePage");
            return "errorPage";
        }
        model.addAttribute("message","删除成功");
        model.addAttribute("url","/admin/manageCoursePage");
        return "correctPage";

    }






}
