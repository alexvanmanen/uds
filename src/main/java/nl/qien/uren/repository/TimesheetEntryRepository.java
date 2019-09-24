package nl.qien.uren.repository;

import nl.qien.uren.entity.TimesheetEntry;
import nl.qien.uren.model.EntryKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Integer> {
    TimesheetEntry findByTimesheetIdAndDayOfTheMonthAndEntryKind(int id, int dayOfTheMonth, EntryKind entryKind);
}
