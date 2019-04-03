package com.choose.global;

import com.choose.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//定时删除过期选课任务
@Component
public class DeleteTask {
    @Autowired
    private UserService userService;
    //每天凌晨0点更新数据库，将过期的选课删除
    @Scheduled(cron = "0 0 0 * * ?")
    public void deletePass()
    {
        userService.removePassChoose();
    }
}