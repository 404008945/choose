package com.choose.dao;


import com.choose.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    //根据周几获取时间安排
    List<Admin> selectByDay(Integer day);

    //通过课程名和天数获取安排信息
    List<Admin> selectByCourseId(@Param("courseId") Integer courseId, @Param("day") Integer day);

    //获取某个老师的所有课程
    List<Admin> selectByTeacherId(Integer teacher);
}