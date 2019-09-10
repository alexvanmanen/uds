package nl.qien.uren.controller;

import nl.qien.uren.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSheetController {

    @Autowired
//    @Qualifier("JDBCTimesheetRepository")
    private TimesheetRepository timesheetRepository;

    @PostMapping("/submitTimeSheet/{id}")
    public void submitTimesheet(@PathVariable int id) {
        //timesheetRepository.findById(id);
    }
}
