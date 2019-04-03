package com.choose.controller;

import com.choose.entity.*;
import com.choose.info.ChooseInfo;
import com.choose.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    //跳转到注册界面
    @RequestMapping("/registerPage")
    public String registerPage()
    {
        return "register";
    }
    //注册
    @RequestMapping("/register")
    public String register(User user, Model model)
    {
        if(user.getAccount()==null||user.getAccount().equals("")||user.getPassword()==null||user.getPassword().equals(""))
        {
            return "register";
        }
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

    //跳转到登录·界面
    @RequestMapping("/loginPage")
    public String loginPage()
    {
        return "login";
    }

    //登录
    @RequestMapping("/chooseByOne")
    public String chooseByOne(Model model){
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses",courses);
      return "stuChooseOne";
    }

    @RequestMapping("/login")
    public String loginPage(Model model, HttpServletRequest request)
    {
        int type=Integer.valueOf(request.getParameter("type"));;
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        if(account!=null&&password!=null&&!"".equals(account)&&!"".equals(password))
        {
            if(type==0)
            {
                User user = loginService.login(account, password, 0);
                if(user!=null)
                {
                    //删除已选课程
                    userService.removePassChoose();
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
        model.addAttribute("admins",plans);
        return "index";
    }

    //获取用户所有选课信息
    @RequestMapping("/choosed")
    public String getAllChoosed(Model model,HttpServletRequest request)
    {
        //模拟注入学生，实际上从session中取
//        User user=new User();
//        user.setId(2);
//        request.getSession().setAttribute("login",user);//从session中获取用户  然后根据其id ，查出所有课程
        User user1 = (User) request.getSession().getAttribute("login");
        //Admin数组需要时间需要转换
        System.out.println(user1.getId());
        List<ChooseInfo> infos = chooseService.getByUserId(user1.getId());//获取infos 所有
        if(infos==null)
        {
            //该学生尚未选课
        }else{
            System.out.println(infos);
        }
        model.addAttribute("admins",infos);
        return "studentChoosed";
    }
    @RequestMapping("/myChoosed")
    public String getMyChoosed(Model model,HttpServletRequest request,@RequestParam(value = "day", required = false)Integer day)
    {
        if(day==null)
        {
            day=1;
        }
        //模拟注入学生，实际上从session中取
//        User user=new User();
//        user.setId(2);
//        request.getSession().setAttribute("login",user);//从session中获取用户  然后根据其id ，查出所有课程
        User user1 = (User) request.getSession().getAttribute("login");
        System.out.println(user1.getId());
        //Admin数组需要时间需要转换
        List<ChooseInfo> infos = chooseService.getByUserId(user1.getId());//获取infos 所有
        if(infos==null)
        {//该学生尚未选课
            infos=new ArrayList<ChooseInfo>();
        }else{
            System.out.println(infos);
        }
        model.addAttribute("infos",infos);
        model.addAttribute("day",day);
        return "myCourses";
    }
    //用户开始选择座位
    @RequestMapping("/chooseSeat")
    public String chooseSeat(Model model, @RequestParam("adminId")  Integer adminId)
    {
        //总座位号
        Integer totalSeat = adminService.getByPrimaryKey(adminId).getTotalSeat();
        //获取所有剩余座位号
        List<Integer> remainSeats = chooseService.getRemainSeats(adminId);
        model.addAttribute("totalSeat",totalSeat);
        model.addAttribute("remainSeats",remainSeats.toString());
        model.addAttribute("adminId",adminId);
        System.out.println(totalSeat);
        System.out.println(remainSeats);
        return "seat";
    }
    //课程和座位号选择后开始预约
    @RequestMapping("/choose")
    public String choose(Model model,HttpServletRequest request)
    {
        //前台需要给我这个参数
        Integer seatNum = Integer.valueOf(request.getParameter("seatNum"));
        Integer adminId= Integer.valueOf(request.getParameter("adminId"));
        Choose choose=new Choose();
        User user= (User) request.getSession().getAttribute("login");//choose是一种关系
        //choose.setUserId(user.getId());
        //虚拟注入
        choose.setUserId(user.getId());//暂时拿不到session
        Admin admin=adminService.getByPrimaryKey(adminId);
        choose.setAdmin(admin);
        choose.setSeatNumber(seatNum);
        //预约成功，跳到用户所选的所有课界面
        int ret=chooseService.add(choose);
        if(ret==-1)
        {
            model.addAttribute("message","选课失败，本课程时间与您已选的成功冲突,请重新选课");
            model.addAttribute("url","/user/chooseByOne");
            return "errorPage";
        }
    else    if(ret==-2)
        {
            model.addAttribute("message","选课失败，该座位已经被选了,请重新选课");
            model.addAttribute("url","/user/chooseByOne");
            return "errorPage";
        }
        else {
            model.addAttribute("message", "选课成功，即将跳转我的课表");
            model.addAttribute("url", "/user/choosed");
            return "correctPage";
        }

    }
    //用户删除某次选课，需传递adminId参数
    @RequestMapping("/remove/{adminId}/{day}")
    public String deleteChoosed(HttpServletRequest request, @PathVariable("adminId")Integer adminId,@PathVariable("day")Integer day,Model model)
    {
      model.addAttribute("message","取消成功");
      model.addAttribute("url","/user/myChoosed?day="+day);
      int userId=((User)request.getSession().getAttribute("login")).getId();
      chooseService.removeByUserIdAndAdminId(userId,adminId);
      return "correctPage";
    }

}
