package com.choose.dao;

import com.choose.entity.Choose;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChooseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Choose record);

    Choose selectByPrimaryKey(Integer id);

    List<Choose> selectAll();

    //通过用户id和时间段id来修改
    int update(Choose choose);

    //获取某个时间段的座位信息
    List<Choose> selectStatusById(Integer adminId);

    //获取某一用户的所有选课情况
    List<Choose> selectByUserId(Integer userId);

    //通过管理id获取剩余座位
    Integer selectRemainSeatsByAdminId(Integer adminId);
}
