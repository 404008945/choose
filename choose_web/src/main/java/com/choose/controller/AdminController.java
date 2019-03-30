package com.choose.controller;

import com.choose.entity.Admin;
import com.choose.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
@RequestBody
public class AdminController {
    @Autowired
    private AdminService adminService;
    //管理员添加时间
    @RequestMapping("/add")
    public String add(Admin admin, Model model)
    {
        int add = adminService.add(admin);
        if(add==-1)
        {
            //时间冲突，告知管理员，重新插入
            model.addAttribute("message","时间冲突，请重新插入");
        }else{
            model.addAttribute("message","插入成功");
        }
        //返回继续插入
        return "";
    }
}
