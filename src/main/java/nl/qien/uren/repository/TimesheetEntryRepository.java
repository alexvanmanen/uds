package nl.qien.uren.repository;

import nl.qien.uren.entity.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Integer> {
}
