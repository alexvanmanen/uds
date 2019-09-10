package nl.qien.uren.controller;

import nl.qien.uren.entity.KeyGenerator;
import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.TimesheetState;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        timesheet.setState(TimesheetState.PENDING);
        timesheet.setCustomerKey(KeyGenerator.generateKey());
        timesheetRepository.save(timesheet);
        new SendMail().sendMail(timesheet);
    }

    @PostMapping("/approveTimesheet/{id}/{customerkey}")
    public void approveTimesheet(@PathVariable int id, @PathVariable String customerkey){
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + id));
        timesheet.setState(TimesheetState.APPROVED);
        timesheetRepository.save(timesheet);
        new SendMail().sendApproveMail(timesheet);
    }

    @GetMapping ("/showTimesheetToCustomer/{customerkey}")
    public Timesheet showTimesheetToCustomer (@PathVariable String customerkey){
        return timesheetRepository.findByCustomerKey(customerkey);
    }


    @PostMapping("/rejectTimesheet/{id}/{customerkey}")
    public void rejectTimesheet(@PathVariable int id, @PathVariable String customerkey){
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new RuntimeException("timesheet not found for this id :: " + id));
        timesheet.setState(TimesheetState.DECLINED);
        timesheetRepository.save(timesheet);
        new SendMail().sendSadMail(timesheet);
    }
}
