package com.choose.dao;

import com.choose.entity.Admin;
import com.choose.entity.Choose;
import org.apache.ibatis.annotations.Param;
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

    //根据userId和adminId来删除用户的某次选课
    int deleteByUserIdAndAdminId(@Param("userId") Integer userId, @Param("adminId") Integer adminId);

    List<Integer> selectChoosedSeatsByAdminId(Integer adminId);

    //通过用户id和day来获取该用户的选课情况,用于做选课冲突
    List<Admin> selectByUserIdAndDay(@Param("userId")Integer userId,@Param("day")Integer day);
}
