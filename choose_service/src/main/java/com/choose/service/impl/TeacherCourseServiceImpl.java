package com.choose.service.impl;

import com.choose.dao.TeacherCourseDao;
import com.choose.entity.TeacherCourse;
import com.choose.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    @Autowired
    private TeacherCourseDao teacherCourseDao;
    public int removeByPrimaryKey(Integer id) {
        return teacherCourseDao.deleteByPrimaryKey(id);
    }

    public int add(TeacherCourse record) {
        return teacherCourseDao.insert(record);
    }

    public TeacherCourse getByPrimaryKey(Integer id) {
        return teacherCourseDao.selectByPrimaryKey(id);
    }

    public List<TeacherCourse> getAll() {
        return teacherCourseDao.selectAll();
    }

    public int editByPrimaryKey(TeacherCourse record) {
        return teacherCourseDao.updateByPrimaryKey(record);
    }
}
