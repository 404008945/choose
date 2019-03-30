import com.choose.dao.AdminDao;
import com.choose.entity.Admin;
import com.choose.entity.Choose;
import com.choose.entity.Course;
import com.choose.entity.Teacher;
import com.choose.info.AdminInfo;
import com.choose.service.AdminService;
import com.choose.service.ChooseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-service.xml")
public class TestDemo {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ChooseService chooseService;
    @Test
    public void test2()
    {
        List<AdminInfo> infos = adminService.getByDay(1);
        for(AdminInfo adminInfo:infos)
        {
            System.out.println(adminInfo);
        }
    }

    @Test
    public void test3() throws ParseException {
        Admin admin=new Admin();
        Course course=new Course();
        course.setName("fcc");
        course.setId(1);
        admin.setCourse(course);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin=format.parse("2019-03-27 10:00:00");
        Date end=format.parse("2019-03-27 10:40:00");
        admin.setBeginTime(begin);
        admin.setEndTime(end);
        Teacher teacher=new Teacher();
        teacher.setName("王");
        teacher.setId(1);
        admin.setTeacher(teacher);
        admin.setTotalSeat(30);
        admin.setDay(1);
        int result=adminService.add(admin);
        System.out.println(result);
    }
}