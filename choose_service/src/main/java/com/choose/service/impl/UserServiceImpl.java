package com.choose.service.impl;

import com.choose.dao.TeacherDao;
import com.choose.dao.UserDao;
import com.choose.entity.User;
import com.choose.service.TeacherService;
import com.choose.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public int removeByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public int add(User record) {
        record.setPassword("123456");
        return userDao.insert(record);
    }

    public User getByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    public List<User> getAll() {
        return userDao.selectAll();
    }

    public int editByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    public User getByAccount(String account) {
        return userDao.selectByAccount(account);
    }
}
