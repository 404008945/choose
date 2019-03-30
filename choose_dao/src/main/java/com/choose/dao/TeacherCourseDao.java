package com.choose.dao;


import com.choose.entity.TeacherCourse;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherCourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer id);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse record);
}