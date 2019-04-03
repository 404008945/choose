package com.choose.service;


import com.choose.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {
    int removeByPrimaryKey(Integer id);

    int add(User record);

    User getByPrimaryKey(Integer id);

    List<User> getAll();

    int editByPrimaryKey(User record);

    User getByAccount(String account);

    public void removePassChoose(Integer userId);
}