package com.choose.controller;

import com.choose.entity.Choose;
import com.choose.service.AdminService;
import com.choose.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/choose")
public class ChooseController {
    @Autowired
    private ChooseService chooseService;
    @Autowired
    private AdminService adminService;
    //修改选课时间
    @RequestMapping("/edit")
    public String edit(Choose choose)
    {
        chooseService.edit(choose);
        return "";
    }
    @RequestMapping("/start")
    public String start(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "AllCourse";
    }
    //删除选课
    public String remove()
    {
        return "";
    }
}
