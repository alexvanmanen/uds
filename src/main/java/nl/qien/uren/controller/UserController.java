package nl.qien.uren.controller;

import nl.qien.uren.entity.*;
import nl.qien.uren.model.SendMail;
import nl.qien.uren.repository.TimesheetRepository;
import nl.qien.uren.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value={ "/showTimesheetToCustomer/{customerkey}"}, method = RequestMethod.GET)
    public ModelAndView showTimesheetToCustomer(@PathVariable String customerkey){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showTimesheetToCustomer");
        Timesheet ts = timesheetRepository.findByCustomerKey(customerkey);
        try {
            modelAndView.addObject("timesheet", ts);
        } catch (NullPointerException e) {
            throw new RuntimeException("No valid timesheet found");
        }
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
        model.addAttribute("name", user.getFirstname());
        model.addAttribute("userId", user.getId());
        model.addAttribute("firstlogin", user.getFirstlogin());
        Timesheet timesheet = timesheetRepository.findById(8)
                .orElseThrow(()-> new RuntimeException("Kan hem niet vinden gap"));
        model.addAttribute("timesheet", timesheet);
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Admin.ROLE_ADMIN)){
            return "redirect:/admin/dashboard";
        }
        return "dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String admindashboard(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("name", user.getFirstname());
        return "admin/dashboard";
    }
    @GetMapping("/admin/manageuser")
    public String manageuser(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
    User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("name", user.getFirstname());
        return "admin/manageuser";
    }
    @GetMapping("/profile")
    public String profile(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("name", user.getFirstname());
        return "profile";
    }
    @GetMapping("/viewprofile")
    public String viewprofile(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("name", user.getFirstname());
        return "viewprofile";
    }

    @RequestMapping(value={"/changePassword/{passwordKey}"}, method = RequestMethod.GET)
    public ModelAndView changePassword(@PathVariable String passwordKey){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("changePassword");
        User user = userRepository.findByPasswordKey(passwordKey);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @RequestMapping(value={"/passwordChange"}, method = RequestMethod.GET)
    public ModelAndView passwordChange(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passwordChange");
        model.addAttribute("name", user.getFirstname());
        return modelAndView;
    }
    @GetMapping("/admin/passwordChange")
    public String passwordChange1(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("name", user.getFirstname());
        return "admin/passwordChange";
    }
}
