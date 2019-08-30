package nl.qien.uren.repository;

import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
}

