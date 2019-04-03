package com.choose.service.impl;

import com.choose.dao.CourseDao;
import com.choose.entity.Course;
import com.choose.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    public int removeByPrimaryKey(Integer id) {
        return courseDao.deleteByPrimaryKey(id);
    }

    public int add(Course record) {
        //课程判重
        String name = record.getName();
        Pattern pattern= Pattern.compile("[a-zA-z]");
        boolean flag = pattern.matcher(name).find();
        List<Course> courses = courseDao.selectAll();
        for(Course course:courses)
        {

            if(course.getName().equals(name))
            {
                //课程冲突
                return -1;
            }
            if(flag&&pattern.matcher(course.getName()).find())
            {
                //存在英文单词
                if(course.getName().toLowerCase().equals(name.toLowerCase()))
                {
                    return -1;
                }
            }
        }
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
