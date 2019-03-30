package com.choose.service;


import com.choose.entity.Choose;

import java.util.List;

public interface ChooseService {
    int removeByPrimaryKey(Integer id);

    int add(Choose record);

    Choose getByPrimaryKey(Integer id);

    List<Choose> getAll();

    int edit(Choose choose);

    List<Choose> getStatusById(Integer adminId);

    //获取某一时间点的剩余座位号
    List<Integer> getRemainSeats(Integer adminId);

    List<Choose> getByUserId(Integer userId);

    Integer getRemainSeatsByAdminId(Integer adminId);
}
