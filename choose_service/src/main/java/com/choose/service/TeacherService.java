package com.choose.service;


import com.choose.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherService {
    int removeByPrimaryKey(Integer id);

    int add(Teacher record);

    Teacher getByPrimaryKey(Integer id);

    List<Teacher> getAll();

    int editByPrimaryKey(Teacher record);

    Teacher getByAccount(String account);
}