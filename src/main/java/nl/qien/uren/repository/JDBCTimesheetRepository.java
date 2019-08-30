package nl.qien.uren.repository;


import nl.qien.uren.model.Timeshit;
import nl.qien.uren.model.TimesheetEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public class JDBCTimesheetRepository implements TimesheetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from Timesheet", Integer.class);
    }

    @Override
    public int save(Timeshit timeshit) {
        int count = 0;

        jdbcTemplate.update(
                "INSERT INTO TIMESHEET(PROJECT_ID, EMPLOYEE_ID, YEAR, MONTH) VALUES(?,?,?,?);",
                timeshit.getProject().getId(), timeshit.getEmployee().getId(), timeshit.getYearMonth().getYear(), timeshit.getYearMonth().getMonthValue());

        Integer timesheetId = jdbcTemplate.queryForObject("SELECT MAX(ID) FROM TIMESHEET", Integer.class);


        for(TimesheetEntry timesheetEntry: timeshit.getEntries()){
            Integer entryKindId = jdbcTemplate.queryForObject("SELECT ID FROM ENTRY_KIND WHERE DESCRIPTION='"+timesheetEntry.getEntryKind().name()+"'", Integer.class);

            count =+ jdbcTemplate.update("INSERT INTO TIMESHEET_ENTRY(TIMESHEET_ID, NUMBER_OF_HOURS, DAY_OF_MONTH, ENTRY_KIND_ID) VALUES (?,?,?,?);",
                    timesheetId, timesheetEntry.getNumberOfHours(), timesheetEntry.getEntryDate().getDayOfMonth(), 1);
        }
        return count;
    }


    @Override
    public Timeshit getTimesheet(Integer employeeId, Integer projectId, YearMonth yearMonth){
        return null;
    }


}
