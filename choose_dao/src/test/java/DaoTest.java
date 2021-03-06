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
    @Autowired
    private ChooseDao chooseDao;
    @Test
    public void test2()
    {
        List<Admin> admins = adminDao.selectByCourseIdAndDay(1, 1);
        System.out.println(admins);
    }

    @Test
    public void test3()
    {
        List<Integer> list = chooseDao.selectChoosedSeatsByAdminId(1);
        System.out.println(list);
    }
}