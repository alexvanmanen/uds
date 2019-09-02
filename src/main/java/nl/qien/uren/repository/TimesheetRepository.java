package nl.qien.uren.repository;

import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

    public List<Timesheet> findAllByProjectId(int projectId);
    public List<Timesheet> findAllByUserId(int projectId);

}

