package com.choose.utils;

import com.choose.dao.ChooseDao;
import com.choose.entity.Admin;
import com.choose.info.AdminInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChooseUtil {
    //Admin列表转换为AdminInfo列表
    public static List<AdminInfo> getAdminInfo(List<Admin> admins, ChooseDao chooseDao) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        List<AdminInfo> infos=new ArrayList<AdminInfo>();
        for(Admin admin:admins)
        {
            AdminInfo adminInfo=new AdminInfo();
            Date beginTime = admin.getBeginTime();
            String beginTimeStr=format.format(beginTime);
            Date endTime = admin.getEndTime();
            String endTimeStr=format.format(endTime);
            adminInfo.setDay(admin.getDay());
            adminInfo.setAdminId(admin.getId());
            adminInfo.setRemainSeats(30-chooseDao.selectRemainSeatsByAdminId(admin.getId()));
            adminInfo.setBeginTime(beginTimeStr);
            adminInfo.setEndTime(endTimeStr);
            adminInfo.setCourseName(admin.getCourse().getName());
            adminInfo.setTeacherName(admin.getTeacher().getName());
            infos.add(adminInfo);
        }
        return infos;
    }
}
