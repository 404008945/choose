package com.choose.controller;

import com.choose.entity.Choose;
import com.choose.info.AdminInfo;
import com.choose.info.WeekInfo;
import com.choose.service.AdminService;
import com.choose.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChooseController {
    @Autowired
    private ChooseService chooseService;
    @Autowired
    private AdminService adminService;
    //进入预选择界面
    @RequestMapping("/preChoosePage")
    public String preChoosePage()
    {
        List<WeekInfo> weekInfos=new ArrayList<>();
        WeekInfo weekInfo1=new WeekInfo(1,adminService.getByDay(1));
        WeekInfo weekInfo2=new WeekInfo(2,adminService.getByDay(2));
        WeekInfo weekInfo3=new WeekInfo(3,adminService.getByDay(3));
        WeekInfo weekInfo4=new WeekInfo(4,adminService.getByDay(4));
        WeekInfo weekInfo5=new WeekInfo(5,adminService.getByDay(5));
        WeekInfo weekInfo6=new WeekInfo(6,adminService.getByDay(6));
        WeekInfo weekInfo7=new WeekInfo(7,adminService.getByDay(7));
        return "";
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

    //开始选课
    @RequestMapping("/choose")
    public String choose()
    {
        return" ";
    }

    //修改选课时间
    @RequestMapping("/edit")
    public String edit(Choose choose)
    {
        chooseService.edit(choose);
        return "";
    }

    //删除选课
    public String remove()
    {
        return "";
    }
}
