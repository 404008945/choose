package com.choose.service;


import com.choose.entity.Course;

import java.util.List;

public interface CourseService {
    int removeByPrimaryKey(Integer id);

    int add(Course record);

    Course getByPrimaryKey(Integer id);

    List<Course> getAll();

    int editByPrimaryKey(Course record);
}