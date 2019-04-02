package com.choose.service.impl;

import com.choose.dao.AdminDao;
import com.choose.dao.ChooseDao;
import com.choose.entity.Admin;
import com.choose.entity.Choose;
import com.choose.info.ChooseInfo;
import com.choose.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChooseServiceImpl implements ChooseService {
    @Autowired
    private ChooseDao chooseDao;

    @Autowired
    private AdminDao adminDao;
    public int removeByPrimaryKey(Integer id) {
        return chooseDao.deleteByPrimaryKey(id);
    }

    public int add(Choose record) {
        Admin admin = record.getAdmin();
        long beginTime = admin.getBeginTime().getTime();
        long endTime = admin.getEndTime().getTime();
        //需要做冲突处理，如果冲突返回-1，否则插入
        List<Admin> admins = chooseDao.selectByUserIdAndDay(record.getUserId(), record.getAdmin().getDay());
        for(Admin admin1:admins)
        {
            if((beginTime>=admin1.getBeginTime().getTime()&&beginTime<admin1.getEndTime().getTime())||
            admin1.getBeginTime().getTime()>=beginTime&&admin1.getBeginTime().getTime()<endTime)
            {
                //有冲突
                return -1;
            }
        }
        return chooseDao.insert(record);
    }

    public Choose getByPrimaryKey(Integer id) {
        return chooseDao.selectByPrimaryKey(id);
    }

    public List<Choose> getAll() {
        return chooseDao.selectAll();
    }

    public int edit(Choose choose) {
        return chooseDao.update(choose);
    }

    public List<Choose> getStatusById(Integer adminId) {
        return chooseDao.selectStatusById(adminId);
    }

    public List<Integer> getRemainSeats(Integer adminId) {
        //获取所有重合的课程安排
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");

        Admin admin = adminDao.selectByPrimaryKey(adminId);
        Integer day = admin.getDay();
        long beginTime = admin.getBeginTime().getTime();
        long endTime = admin.getEndTime().getTime();
        System.out.println("指定开始:"+format.format(admin.getBeginTime()));
        System.out.println("指定结束:"+format.format(admin.getEndTime()));
        //获取该天的所有课程
        List<Admin> admins = adminDao.selectByDay(day);
        //注入自己的admin
        admins.add(admin);
        List<Integer> choosed=new ArrayList<Integer>();
        for(Admin admin1:admins)
        {
            if((admin1.getBeginTime().getTime()>=beginTime&&admin1.getBeginTime().getTime()<endTime)
            ||(beginTime>=admin1.getBeginTime().getTime()&&beginTime<admin1.getEndTime().getTime()))
            {
                System.out.println("冲突"+admin1.getId());
                System.out.println("开始"+format.format(admin1.getBeginTime()));
                System.out.println("结束"+format.format(admin1.getEndTime()));
                System.out.println("-------------------");
                //该插入时间与以数据库安排时间重合所选的座位
                List<Integer> choosedSeats = chooseDao.selectChoosedSeatsByAdminId(admin1.getId());
                choosed.addAll(choosedSeats);
            }
        }
        List<Integer> list1=new ArrayList<Integer>();
        for(int i=1;i<=adminDao.selectAll().get(0).getTotalSeat();i++)
        {
            list1.add(i);
        }
        list1.removeAll(choosed);
        return list1;
    }

    public List<ChooseInfo> getByUserId(Integer userId) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        List<Choose> chooses = chooseDao.selectByUserId(userId);
        List<ChooseInfo> infos=new ArrayList<ChooseInfo>();
        for(Choose choose:chooses)
        {
            ChooseInfo chooseInfo=new ChooseInfo();
            chooseInfo.setAdminId(choose.getAdmin().getId());
            String beginTime=format.format(choose.getAdmin().getBeginTime());
            String endTime=format.format(choose.getAdmin().getEndTime());
            chooseInfo.setBeginTime(beginTime);
            chooseInfo.setCourseName(choose.getAdmin().getCourse().getName());
            chooseInfo.setSeat(choose.getSeatNumber());
            chooseInfo.setDay(choose.getAdmin().getDay());
            chooseInfo.setEndTime(endTime);
            chooseInfo.setTeacherName(choose.getAdmin().getTeacher().getName());
            infos.add(chooseInfo);
        }
        return infos;
    }

    public Integer getRemainSeatsByAdminId(Integer adminId) {
        return chooseDao.selectRemainSeatsByAdminId(adminId);
    }

    public int removeByUserIdAndAdminId(Integer userId, Integer adminId) {
        return chooseDao.deleteByUserIdAndAdminId(userId,adminId);
    }

    public List<Admin> getByUserIdAndDay(Integer userId, Integer day) {
        return chooseDao.selectByUserIdAndDay(userId,day);
    }
}
