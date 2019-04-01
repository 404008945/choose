package com.choose.controller;

import com.choose.entity.*;
import com.choose.info.ChooseInfo;
import com.choose.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ChooseService chooseService;
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
                    //设置学生标记
                    request.getSession().setAttribute("type",0);
                    return "stuChooseOne";
                }

            }else if(type==1)
            {
                Teacher teacher=loginService.login(account,password,1);
                if(teacher!=null) {
                    //登录成功
                    //课程管理界面
                    request.getSession().setAttribute("login",teacher);
                    //设置老师标记
                    request.getSession().setAttribute("type",1);
                    return "redirect:/admin/detailByDay/1";
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
        System.out.println(plans);
        model.addAttribute("admins",plans);
        return "index";
    }

    //获取用户所有选课信息
    @RequestMapping("/choosed")
    public String getAllChoosed(Model model,HttpServletRequest request)
    {
        //模拟注入学生，实际上从session中取
        User user=new User();
        user.setId(2);
        request.getSession().setAttribute("login",user);
        User user1 = (User) request.getSession().getAttribute("login");
        //Admin数组需要时间需要转换
        List<ChooseInfo> infos = chooseService.getByUserId(user1.getId());
        if(infos==null)
        {
            //该学生尚未选课
        }else{

        }
        return "studentChoosed";
    }

    //用户开始选择座位
    @RequestMapping("/chooseSeat")
    public String choose(Model model)
    {
        Integer adminId=2;
        //总座位号
        Integer totalSeat = adminService.getByPrimaryKey(adminId).getTotalSeat();
        //获取所有剩余座位号
        List<Integer> remainSeats = chooseService.getRemainSeats(adminId);
        System.out.println(totalSeat);
        System.out.println(remainSeats);
        return "seat";
    }

    //用户删除某次选课，需传递adminId参数
    @RequestMapping("/remove/{adminId}")
    public String deleteChoosed(@PathVariable("adminId")Integer adminId)
    {

        return "forward:/user/choosed";
    }

}
