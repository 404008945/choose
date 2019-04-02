package com.choose.service.impl;

import com.choose.dao.TeacherDao;
import com.choose.entity.Teacher;
import com.choose.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    public int removeByPrimaryKey(Integer id) {
        return teacherDao.deleteByPrimaryKey(id);
    }

    public int add(Teacher record) {
        //设置教师默认密码
        record.setPassword("123456");
        return teacherDao.insert(record);
    }

    public Teacher getByPrimaryKey(Integer id) {
        return teacherDao.selectByPrimaryKey(id);
    }

    public List<Teacher> getAll() {
        return teacherDao.selectAll();
    }

    public int editByPrimaryKey(Teacher record) {
        return teacherDao.updateByPrimaryKey(record);
    }

    public Teacher getByAccount(String account) {
        return teacherDao.selectByAccount(account);
    }
}
