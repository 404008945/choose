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
        record.setTotalSeat(30);
        Integer day = record.getDay();
        Integer courseId= record.getCourse().getId();
        List<Admin> admins = adminDao.selectByCourseIdAndDay(courseId,day);
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
        List<Admin> admins = adminDao.selectByDay(day);
        return common(admins);
    }

    public List<Admin> getByCourseIdAndDay(Integer courseId,Integer day) {
        return adminDao.selectByCourseIdAndDay(courseId,day);
    }

    public List<AdminInfo> getByTeacherId(Integer teacherId) {
        List<Admin> admins = adminDao.selectByTeacherId(teacherId);
        return common1(admins);
    }

    //提取出来的通用方法
    public List<AdminInfo> common(List<Admin> admins)
    {
        List<AdminInfo> infos=new ArrayList<AdminInfo>();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        for(Admin admin:admins)
        {
            AdminInfo adminInfo=new AdminInfo();
            Date beginTime = admin.getBeginTime();
            String beginTimeStr=format.format(beginTime);
            Date endTime = admin.getEndTime();
            String endTimeStr=format.format(endTime);
            adminInfo.setDay(admin.getDay());
            adminInfo.setAdminId(admin.getId());
            adminInfo.setRemainSeats(getRemainsSeat(admin.getId()));
            adminInfo.setBeginTime(beginTimeStr);
            adminInfo.setEndTime(endTimeStr);
            adminInfo.setCourseName(admin.getCourse().getName());
            adminInfo.setTeacherName(admin.getTeacher().getName());
            infos.add(adminInfo);
        }
        return infos;
    }

    public List<AdminInfo> common1(List<Admin> admins)
    {
        List<AdminInfo> infos=new ArrayList<AdminInfo>();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        for(Admin admin:admins)
        {
            AdminInfo adminInfo=new AdminInfo();
            Date beginTime = admin.getBeginTime();
            String beginTimeStr=format.format(beginTime);
            Date endTime = admin.getEndTime();
            String endTimeStr=format.format(endTime);
            adminInfo.setDay(admin.getDay());
            adminInfo.setAdminId(admin.getId());
            //已选座位数
            adminInfo.setRemainSeats(getChooseNum(admin.getId()));
            adminInfo.setBeginTime(beginTimeStr);
            adminInfo.setEndTime(endTimeStr);
            adminInfo.setCourseName(admin.getCourse().getName());
            adminInfo.setTeacherName(admin.getTeacher().getName());
            infos.add(adminInfo);
        }
        return infos;
    }

    //获取某一次课的选课人数
    public  int getChooseNum(int adminId){
        return  chooseDao.selectChooseNumByAdminId(adminId);
    }

    public  int getRemainsSeat(int adminId){
        List<Admin> admins=adminDao.selectAll();
        Admin admin=adminDao.selectByPrimaryKey(adminId);
        long beginTime = admin.getBeginTime().getTime();
        long endTime = admin.getEndTime().getTime();
        int count=0;

        for(Admin a:admins)
        {
            boolean flag=false;
            if((a.getBeginTime().getTime()>=beginTime&&a.getBeginTime().getTime()<endTime) ||(beginTime>=a.getBeginTime().getTime()&&beginTime<a.getEndTime().getTime()))
                flag=true;
            if(a.getDay()==admin.getDay()&&flag)
            {
                 count+=chooseDao.selectRemainSeatsByAdminId(a.getId());
            }

        }
      return  30-count;
    }

    public List<Admin> getByCourseId(Integer courseId) {
        return adminDao.selectByCourseId(courseId);
    }
}
