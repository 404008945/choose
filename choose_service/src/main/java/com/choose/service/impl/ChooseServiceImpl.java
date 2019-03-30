package com.choose.service.impl;

import com.choose.dao.AdminDao;
import com.choose.dao.ChooseDao;
import com.choose.entity.Choose;
import com.choose.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChooseServiceImpl implements ChooseService {
    @Autowired
    private ChooseDao chooseDao;

    @Autowired
    private AdminDao adminDao;
    public int removeByPrimaryKey(Integer id) {
        return chooseDao.deleteByPrimaryKey(id);
    }

    public int add(Choose record) {
        return chooseDao.insert(record);
    }

    public Choose getByPrimaryKey(Integer id) {
        return chooseDao.selectByPrimaryKey(id);
    }

    public List<Choose> getAll() {
        return chooseDao.selectAll();
    }

    public int edit(Choose choose) {
        return chooseDao.update(choose);
    }

    public List<Choose> getStatusById(Integer adminId) {
        return chooseDao.selectStatusById(adminId);
    }

    public List<Integer> getRemainSeats(Integer adminId) {
        List<Integer> list1=new ArrayList<Integer>();
        for(int i=1;i<=adminDao.selectAll().get(0).getTotalSeat();i++)
        {
            list1.add(i);
        }
        List<Integer> list2=new ArrayList<Integer>();
        List<Choose> chooses = chooseDao.selectStatusById(adminId);
        for(Choose choose:chooses)
        {
            list2.add(choose.getSeatNumber());
        }
        list1.removeAll(list2);
        return list1;
    }

    public List<Choose> getByUserId(Integer userId) {
        return chooseDao.selectByUserId(userId);
    }

    public Integer getRemainSeatsByAdminId(Integer adminId) {
        return chooseDao.selectRemainSeatsByAdminId(adminId);
    }
}
