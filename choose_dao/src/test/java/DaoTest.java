import com.choose.dao.AdminDao;
import com.choose.dao.ChooseDao;
import com.choose.entity.Admin;
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
@ContextConfiguration("classpath:spring-dao.xml")
public class DaoTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void test2()
    {
        List<Admin> admins = adminDao.selectByCourseIdAndDay(1, 1);
        System.out.println(admins);
    }
}