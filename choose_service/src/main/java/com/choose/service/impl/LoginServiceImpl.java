package com.choose.service.impl;

import com.choose.dao.TeacherDao;
import com.choose.dao.UserDao;
import com.choose.entity.Teacher;
import com.choose.entity.User;
import com.choose.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeacherDao teacherDao;
    public <T> T login(String account, String password,int type) {
        if(type==0)
        {
            User user=userDao.selectByAccount(account);
            if(user!=null&&user.getPassword().equals(password))
            {
                return (T) user;
            }else{
                return null;
            }
        }else{
            Teacher teacher=teacherDao.selectByAccount(account);
            if(teacher!=null&&teacher.getPassword().equals(password))
            {
                return (T) teacher;
            }else{
                return null;
            }
        }
    }
}
