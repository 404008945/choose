package com.choose.service;


import com.choose.entity.Admin;
import com.choose.entity.Choose;
import com.choose.info.ChooseInfo;
import org.apache.ibatis.annotations.Param;

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

    List<ChooseInfo> getByUserId(Integer userId);

    Integer getRemainSeatsByAdminId(Integer adminId);

    int removeByUserIdAndAdminId(Integer userId,Integer adminId);

    List<Admin> getByUserIdAndDay(Integer userId, Integer day);

    List<Integer> getChoosedSeatsByAdminId(Integer adminId);
}
