package nl.qien.uren.repository;

import nl.qien.uren.entity.Timesheet;

import java.time.YearMonth;

public interface TimesheetRepository {

    int count();
    int save(Timesheet TS);
    Timesheet getTimesheet(Integer employeeId, Integer projectId, YearMonth yearMonth);

}
