package com.choose.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //用户注册功能
    @RequestMapping("/registerPage")
    public String registerPage()
    {
        return "register";
    }
    //注册
    public String register()
    {
        return "";
    }
}
