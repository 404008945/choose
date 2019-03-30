package com.choose.service;


import com.choose.entity.TeacherCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseService {
    int removeByPrimaryKey(Integer id);

    int add(TeacherCourse record);

    TeacherCourse getByPrimaryKey(Integer id);

    List<TeacherCourse> getAll();

    int editByPrimaryKey(TeacherCourse record);
}