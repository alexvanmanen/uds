package nl.qien.uren.repository;

import nl.qien.uren.model.Timesheet;
import nl.qien.uren.model.TimesheetEntry;

import java.time.YearMonth;

public interface TimesheetRepository {

    int count();
    int save(Timesheet timesheet);
    Timesheet getTimesheet(Integer employeeId, Integer projectId, YearMonth yearMonth);

}
