package nl.qien.uren.repository;

import nl.qien.uren.entity.TimeSheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetEntryRepository extends JpaRepository<TimeSheetEntry, Integer> {
}
