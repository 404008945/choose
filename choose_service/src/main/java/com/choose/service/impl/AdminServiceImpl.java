package com.choose.service.impl;

import com.choose.dao.AdminDao;
import com.choose.dao.ChooseDao;
import com.choose.dao.TeacherCourseDao;
import com.choose.entity.Admin;
import com.choose.entity.TeacherCourse;
import com.choose.info.AdminInfo;
import com.choose.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ChooseDao chooseDao;

    @Autowired
    private TeacherCourseDao teacherCourseDao;
    public int removeByPrimaryKey(Integer id) {
        return adminDao.deleteByPrimaryKey(id);
    }

    public int add(Admin record) {
        //进行时间判断,防止时间段重合
        System.out.println(record);
        Integer day = record.getDay();
        Integer courseId= record.getCourse().getId();
        List<Admin> admins = adminDao.selectByCourseId(courseId,day);
        boolean flag=true;
        if(admins.size()!=0)
        {
            //默认可以安排
            for(Admin admin:admins)
            {
                if((record.getBeginTime().getTime()>=admin.getBeginTime().getTime()&&record.getBeginTime().getTime()<admin.getEndTime().getTime())
                        ||(admin.getBeginTime().getTime()>=record.getBeginTime().getTime()&&
                        admin.getBeginTime().getTime()<record.getEndTime().getTime()))
                {
                    //时间冲突
                    flag=false;
                    break;
                }
            }
        }
        if(!flag)
        {
            return -1;
        }
        //维护教师课程表
        TeacherCourse teacherCourse=new TeacherCourse();
        teacherCourse.setCourseId(record.getCourse().getId());
        teacherCourse.setTeacherId(record.getTeacher().getId());
        teacherCourseDao.insert(teacherCourse);
        return adminDao.insert(record);
    }

    public Admin getByPrimaryKey(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }

    public List<Admin> getAll() {
        return adminDao.selectAll();
    }

    public int editByPrimaryKey(Admin record) {
        return adminDao.updateByPrimaryKey(record);
    }

    public List<AdminInfo> getByDay(Integer day) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        List<AdminInfo> infos=new ArrayList<AdminInfo>();
        List<Admin> admins = adminDao.selectByDay(day);
        for(Admin admin:admins)
        {
            AdminInfo adminInfo=new AdminInfo();
            Date beginTime = admin.getBeginTime();
            String beginTimeStr=format.format(beginTime);
            Date endTime = admin.getEndTime();
            String endTimeStr=format.format(endTime);
            adminInfo.setDay(day);
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

    public List<Admin> getByCourseId(Integer courseId,Integer day) {
        return adminDao.selectByCourseId(courseId,day);
    }

    public List<AdminInfo> getByTeacherId(Integer teacherId) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        List<AdminInfo> infos=new ArrayList<AdminInfo>();
        List<Admin> admins = adminDao.selectByTeacherId(teacherId);
        for(Admin admin:admins)
        {
            AdminInfo adminInfo=new AdminInfo();
            Date beginTime = admin.getBeginTime();
            String beginTimeStr=format.format(beginTime);
            Date endTime = admin.getEndTime();
            String endTimeStr=format.format(endTime);
            adminInfo.setDay(admin.getDay());
            adminInfo.setBeginTime(beginTimeStr);
            adminInfo.setEndTime(endTimeStr);
            adminInfo.setCourseName(admin.getCourse().getName());
            adminInfo.setTeacherName(admin.getTeacher().getName());
            infos.add(adminInfo);
        }
        return infos;
    }
}
