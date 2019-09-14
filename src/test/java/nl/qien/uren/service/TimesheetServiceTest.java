package nl.qien.uren.service;

import nl.qien.uren.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.YearMonth;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceTest {

    @Autowired
    TimesheetService timesheetService;

    @Test
    public void employeeHasNoTimesheetTest() {
        //No arrange for this test (except of the autowired timesheetService)
        boolean result = timesheetService.employeeHasNoTimesheet(new Employee(), null); //Act
        Assert.assertEquals(false, result); //Assert
    }

    @Test
    public void employeeHasNoTimesheetTestWithValidEmployee() {
        Employee employee = new Employee("Alex", "van Manen", "alex@alex.nl", "bcrypt wachtwoordje"); //Arrange
        boolean result = timesheetService.employeeHasNoTimesheet(employee, YearMonth.of(2019,01)); //Act
        Assert.assertEquals(false, result); //Assert
    }

}
