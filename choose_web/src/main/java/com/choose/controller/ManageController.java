package com.choose.controller;

import com.choose.info.AdminInfo;
import com.choose.service.AdminService;
import com.choose.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//管理员控制器
@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ChooseService chooseService;
    //进入某一天课详情界面
    @RequestMapping("/detailByDay/{day}")
    public String detail(@PathVariable("day")Integer day, Model model)
    {
        List<AdminInfo> infos = adminService.getByDay(day);
        model.addAttribute("infos",infos);
        model.addAttribute("day",day);
        return "AllCourse";
    }

    //删除某次课
    @RequestMapping("/removeCourse/{adminId}")
    public String remove(@PathVariable("adminId")Integer adminId,Model model)
    {
        adminService.removeByPrimaryKey(adminId);
        return "redirect:/manage/detailByDay/1";
    }
}
