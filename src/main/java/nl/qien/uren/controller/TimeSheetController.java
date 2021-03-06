package nl.qien.uren.controller;

import nl.qien.uren.entity.KeyGenerator;
import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.TimesheetState;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSheetController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @PostMapping("/submitTimeSheet/{id}")
    public void submitTimesheet(@PathVariable int id) {
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + id));
        timesheet.setState(TimesheetState.VERZONDEN);
        timesheet.setCustomerKey(KeyGenerator.generateKey());
        timesheetRepository.save(timesheet);
        new SendMail().sendMail(timesheet);
    }

    @PostMapping("/approveTimesheet/{id}/{customerkey}")
    public void approveTimesheet(@PathVariable int id, @PathVariable String customerkey) {
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + id));
        if (timesheet.getCustomerKey().equals(customerkey)) {
            timesheet.setState(TimesheetState.GOEDGEKEURD);
            timesheet.setCustomerKey(null);
            timesheetRepository.save(timesheet);
            new SendMail().sendApproveMail(timesheet);
        }
        else {
            new RuntimeException("No timesheet eligable for rejection/approval");
        }
    }

    @PostMapping("/rejectTimesheet/{id}/{customerkey}")
    public void rejectTimesheet(@PathVariable int id, @PathVariable String customerkey){
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + id));
        if (timesheet.getCustomerKey().equals(customerkey)) {
            timesheet.setCustomerKey(null);
            timesheet.setState(TimesheetState.AFGEKEURD);
            timesheetRepository.save(timesheet);
            new SendMail().sendSadMail(timesheet);
        }
        else {
            new RuntimeException("No timesheet eligable for rejection/approval");
        }
    }
}
