package com.choose.service;

import com.choose.entity.Admin;
import com.choose.info.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    int removeByPrimaryKey(Integer id);

    int add(Admin record);

    Admin getByPrimaryKey(Integer id);

    List<Admin> getAll();

    int editByPrimaryKey(Admin record);

    List<AdminInfo> getByDay(Integer day);

    List<Admin> getByCourseIdAndDay(Integer courseId,Integer day);

    List<AdminInfo> getByTeacherId(Integer teacherId);

    List<Admin> getByCourseId(@Param("courseId") Integer courseId);

}
