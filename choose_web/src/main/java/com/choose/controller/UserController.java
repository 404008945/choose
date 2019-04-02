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

    //跳转到注册界面
    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    //注册
    @RequestMapping("/register")
    public String register(User user, Model model) {
        User user1 = userService.getByAccount(user.getAccount());
        if (user1 != null) {
            //账户已经被注册
            model.addAttribute("msg", "该账号已被注册,换一个吧");
            model.addAttribute("user", user);
            return "register";
        } else {
            userService.add(user);
            model.addAttribute("user", user);
            return "login";
        }
    }

    //跳转到登录界面
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        int type = 0;
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        if (account != null && password != null && !"".equals(account) && !"".equals(password)) {
            if (type == 0) {
                User user = loginService.login(account, password, 0);
                if (user != null) {
                    //登录成功
                    //选课
                    List<Course> courses = courseService.getAll();
                    model.addAttribute("courses", courses);
                    request.getSession().setAttribute("login", user);
                    //设置学生标记
                    request.getSession().setAttribute("type", 0);
                    return "stuChooseOne";
                }

            } else if (type == 1) {
                Teacher teacher = loginService.login(account, password, 1);
                if (teacher != null) {
                    //登录成功
                    //课程管理界面
                    request.getSession().setAttribute("login", teacher);
                    //设置老师标记
                    request.getSession().setAttribute("type", 1);
                    return "redirect:/admin/detailByDay/1";
                }
            }
        }
        model.addAttribute("msg", "账户名或密码错误，请重新登录");
        return "login";
    }

    //选中某一门课
    @RequestMapping("/chooseOne")
    public String choosePage(Model model, HttpServletRequest request) {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        List<Admin> plans = adminService.getByCourseId(courseId);
        model.addAttribute("admins", plans);
        return "index";
    }

    //获取用户所有选课信息
    @RequestMapping("/choosed")
    public String getAllChoosed(Model model, HttpServletRequest request) {
        //模拟注入学生，实际上从session中取
        User user = new User();
        user.setId(2);
        request.getSession().setAttribute("login", user);
        User user1 = (User) request.getSession().getAttribute("login");
        //Admin数组需要时间需要转换
        List<ChooseInfo> infos = chooseService.getByUserId(user1.getId());
        if (infos == null) {
            //该学生尚未选课
        } else {
            System.out.println(infos);
        }
        return "studentChoosed";
    }

    //用户开始选择座位
    @RequestMapping("/chooseSeat")
    public String chooseSeat(Model model) {
        Integer adminId = 1;
        //总座位号
        Integer totalSeat = adminService.getByPrimaryKey(adminId).getTotalSeat();
        //获取所有剩余座位号
        List<Integer> remainSeats = chooseService.getRemainSeats(adminId);
        model.addAttribute("totalSeat", totalSeat);
        model.addAttribute("remainSeats", remainSeats.toString());
        System.out.println(totalSeat);
        System.out.println(remainSeats);
        return "seat";
    }

    //课程和座位号选择后开始预约
    @RequestMapping("/choose")
    public String choose(Model model, HttpServletRequest request) {
        //前台需要给我这个参数
        //Integer seatNum = Integer.valueOf(request.getParameter("seatNum"));
        //Integer adminId= Integer.valueOf(request.getParameter("adminId"));
        Choose choose = new Choose();
        User user = (User) request.getSession().getAttribute("login");
        //choose.setUserId(user.getId());
        //虚拟注入
        choose.setUserId(1);
        Admin admin = adminService.getByPrimaryKey(1);
        choose.setAdmin(admin);
        choose.setSeatNumber(3);
        //预约成功，跳到支付界面，需要将adminId和seatNumber放在域中
        //因为payResult需要该参数来保存选课信息，推荐session
        //最后再删除session
        return "pay";
    }

    //支付结果
    @RequestMapping("/payResult")
    public String payResult(Model model,HttpServletRequest request)
    {
        //前台需要传该参数来获取支付结果
        Integer result = Integer.valueOf(request.getParameter("result"));
        if(result==1)
        {
            //支付成功
            Integer adminId=1;
            Integer seatNumber=1;
            Admin admin = adminService.getByPrimaryKey(adminId);
            Choose choose=new Choose();
            choose.setAdmin(admin);
            choose.setSeatNumber(seatNumber);
            //从session域中取用户
            choose.setUserId(1);
            //设置choose参数并保存
            int i=chooseService.add(choose);
            if(i==-1)
            {
                //选课时间冲突，告知用户
                model.addAttribute("message","选课时间与您已选的相关课程冲突，请重新选择");
            }else{
                //选课成功，跳转到用户所选择的课程界面
                model.addAttribute("message","选课成功");
            }
        }else{
            //支付失败，跳转信息提示界面或再支付界面
        }
        return "";
    }

    //用户删除某次选课，需传递adminId参数
    @RequestMapping("/remove/{adminId}")
    public String deleteChoosed(@PathVariable("adminId") Integer adminId, HttpServletRequest request) {
        //删除后再次跳回自己选课列表
        User user = (User) request.getSession().getAttribute("login");
        chooseService.removeByUserIdAndAdminId(user.getId(), adminId);
        return "forward:/user/choosed";
    }

}
