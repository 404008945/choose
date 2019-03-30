package com.choose.service.impl;

import com.choose.dao.CourseDao;
import com.choose.entity.Course;
import com.choose.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    public int removeByPrimaryKey(Integer id) {
        return courseDao.deleteByPrimaryKey(id);
    }

    public int add(Course record) {
        return courseDao.insert(record);
    }

    public Course getByPrimaryKey(Integer id) {
        return courseDao.selectByPrimaryKey(id);
    }

    public List<Course> getAll() {
        return courseDao.selectAll();
    }

    public int editByPrimaryKey(Course record) {
        return courseDao.updateByPrimaryKey(record);
    }
}
