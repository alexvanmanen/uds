package nl.qien.uren.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceTest {

    @Test
    public void employeeHasNoTimesheetTest() {
        TimesheetService timesheetService = new TimesheetService();
        boolean result = timesheetService.employeeHasNoTimesheet(null, null);
        Assert.assertEquals(false, result);
    }

}
