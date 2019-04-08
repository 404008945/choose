import com.choose.entity.*;
import com.choose.info.AdminInfo;
import com.choose.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-service.xml")
public class TestDemo {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ChooseService chooseService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Test
    public void test2()
    {
        List<AdminInfo> infos = adminService.getByDay(1);
        for(AdminInfo adminInfo:infos)
        {
            System.out.println(adminInfo);
        }
    }
    //登录功能测试
    @Test
    public void test4()
    {
        User user = loginService.login("gea", "geag",0);
        Teacher teacher=loginService.login("123","123",1);
        System.out.println(user);
        System.out.println(teacher);
    }
    //安排课程功能测试
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
    //选座功能测试
    @Test
    public void testSeats()
    {
        List<Integer> remainSeats = chooseService.getRemainSeats(11);
        System.out.println(remainSeats);
    }

    //用户选课冲突测试
    @Test
    public void test()
    {
        Choose choose=new Choose();
        choose.setAdmin(adminService.getByPrimaryKey(18));
        choose.setUserId(1);
        int add = chooseService.add(choose);
        System.out.println(add);
    }

    //事务测试
    @Test
    public void test1()
    {
        User user=new User();
        user.setPassword("r3rw");
        user.setAccount("geqttqe");
        user.setName("你好");
        user.setPhone("2141212412");
        userService.add(user);
    }
    //课程添加测试
    @Test
    public void test5()
    {
        Course course=new Course();
        course.setName("算法学习");
        int add = courseService.add(course);
        System.out.println(add);
    }

    //添加教师测试
    @Test
    public void test6()
    {
        Teacher teacher=new Teacher();
        teacher.setAccount("123");
        teacher.setName("你好");
        int add = teacherService.add(teacher);
        System.out.println(add);
    }
    //座位冲突测试
    @Test
    public void test7()
    {
        Choose choose=new Choose();
        Admin admin = adminService.getByPrimaryKey(8);
        choose.setAdmin(admin);
        choose.setUserId(3);
        choose.setSeatNumber(17);
        int add = chooseService.add(choose);
        System.out.println(add);
    }

    //日期测试
    @Test
    public void test8()
    {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(i-1);
    }

    //过期选课删除测试
    @Test
    public void test9()
    {
        userService.removePassChoose();
    }

    //获取某门课程的选课座位
    @Test
    public void test10()
    {
        List<Integer> seats = chooseService.getChoosedSeatsByAdminId(1);
        System.out.println(seats);
    }
}
