package com.choose.controller;

import com.choose.entity.Admin;
import com.choose.entity.Course;
import com.choose.entity.Teacher;
import com.choose.entity.User;
import com.choose.service.AdminService;
import com.choose.service.CourseService;
import com.choose.service.LoginService;
import com.choose.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AdminService adminService;
    //用户注册功能
    @RequestMapping("/registerPage")
    public String registerPage()
    {
        return "register";
    }
    //注册
    @RequestMapping("/register")
    public String register(User user, Model model)
    {
        User user1 = userService.getByAccount(user.getAccount());
        if(user1!=null)
        {
            //账户已经被注册
            model.addAttribute("msg","该账号已被注册,换一个吧");
            model.addAttribute("user",user);
            return "register";
        }else{
            userService.add(user);
            model.addAttribute("user",user);
            return "login";
        }
    }

    @RequestMapping("/loginPage")
    public String loginPage()
    {
        return "login";
    }

    @RequestMapping("/login")
    public String loginPage(Model model, HttpServletRequest request)
    {
        int type=0;
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        if(account!=null&&password!=null&&!"".equals(account)&&!"".equals(password))
        {
            if(type==0)
            {
                User user = loginService.login(account, password, 0);
                if(user!=null)
                {
                    //登录成功
                    //选课
                    List<Course> courses = courseService.getAll();
                    model.addAttribute("courses",courses);
                    request.getSession().setAttribute("login",user);
                    return "stuChooseOne";
                }

            }else if(type==1)
            {
                Teacher teacher=loginService.login(account,password,1);
                if(teacher!=null) {
                    //登录成功
                    //课程管理界面
                    request.getSession().setAttribute("login",teacher);
                }
            }
        }
        model.addAttribute("msg","账户名或密码错误，请重新登录");
        return "login";
    }

    //选中某一门课
    @RequestMapping("/chooseOne")
    public String choosePage(Model model,HttpServletRequest request)
    {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        List<Admin> plans = adminService.getByCourseId(courseId);
        return "index";
    }

    //用户开始选择座位
    @RequestMapping("/chooseSeat")
    public String choose(Model model)
    {
        
        return "";
    }
}
