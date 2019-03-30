package com.choose.dao;


import com.choose.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);
}