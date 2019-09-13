package nl.qien.uren.controller;

import nl.qien.uren.entity.Admin;
import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.User;
import nl.qien.uren.repository.TimesheetRepository;
import nl.qien.uren.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value={ "/showTimesheetToCustomer/{customerkey}"}, method = RequestMethod.GET)
    public ModelAndView showTimesheetToCustomer(@PathVariable String customerkey){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showTimesheetToCustomer");
        Timesheet ts = timesheetRepository.findByCustomerKey(customerkey);
        modelAndView.addObject("timesheet", ts);
        return modelAndView;
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(user.getId());
        model.addAttribute("name", name);
        model.addAttribute("test", "Bart Zwaagstra");
        model.addAttribute("userId", user.getId());
        Timesheet timesheet = timesheetRepository.findById(8)
                .orElseThrow(()-> new RuntimeException("Kan hem niet vinden gap"));
        model.addAttribute("timesheet", timesheet);

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Admin.ROLE_ADMIN)){
            System.out.println("admin");
            return "redirect:/admin/dashboard";
        }
        System.out.println("test");
        return "dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String admindashboard(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        model.addAttribute("name", name);
        return "admin/dashboard";
    }
    @GetMapping("/admin/manageuser")
    public String manageuser(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        model.addAttribute("name", name);
        return "admin/manageuser";
    }
    @GetMapping("/profile")
    public String profile(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("name", name);
        return "profile";
    }

}
