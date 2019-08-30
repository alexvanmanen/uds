package nl.qien.uren.repository;


import nl.qien.uren.entity.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public class JDBCTSRepository implements TimesheetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from Timesheet", Integer.class);
    }

    @Override
    public int save(Timesheet TS) {
        int count = 0;

        jdbcTemplate.update(
                "INSERT INTO TIMESHEET(PROJECT_ID, EMPLOYEE_ID, YEAR, MONTH) VALUES(?,?,?,?);",
                TS.getProject().getId(), TS.getEmployee().getId(), TS.getYearMonth().getYear(), TS.getYearMonth().getMonthValue());

        Integer timesheetId = jdbcTemplate.queryForObject("SELECT MAX(ID) FROM TIMESHEET", Integer.class);


//        for(TS_ENTRY TSENTRY : TS.getEntries()){
//            Integer entryKindId = jdbcTemplate.queryForObject("SELECT ID FROM ENTRY_KIND WHERE DESCRIPTION='"+ TSENTRY.getEntryKind().name()+"'", Integer.class);
//
//            count =+ jdbcTemplate.update("INSERT INTO TIMESHEET_ENTRY(TIMESHEET_ID, NUMBER_OF_HOURS, DAY_OF_MONTH, ENTRY_KIND_ID) VALUES (?,?,?,?);",
//                    timesheetId, TSENTRY.getNumberOfHours(), TSENTRY.getEntryDate().getDayOfMonth(), 1);
//        }
        return count;
    }


    @Override
    public Timesheet getTimesheet(Integer employeeId, Integer projectId, YearMonth yearMonth){
        return null;
    }


}