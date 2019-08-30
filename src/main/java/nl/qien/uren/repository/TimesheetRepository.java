package nl.qien.uren.repository;

import nl.qien.uren.model.Timeshit;

import java.time.YearMonth;

public interface TimesheetRepository {

    int count();
    int save(Timeshit timeshit);
    Timeshit getTimesheet(Integer employeeId, Integer projectId, YearMonth yearMonth);

}
