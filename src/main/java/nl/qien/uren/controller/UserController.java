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
    @PostMapping("/forgotPassword")
    public void forgotPassword(@RequestBody User userDetails) {
        System.out.println("userDetails = " + userDetails.getUsername());
        User user = userRepository.findByUsername(userDetails.getUsername());
        user.setPasswordKey(KeyGenerator.generateKey());
        userRepository.save(user);
        new SendMail().sendMail(user);
    }
    @PostMapping("/changePassword/{passwordKey}")
    public User createUser(@RequestBody User userDetails, @PathVariable String passwordKey) {
        User user = userRepository.findByPasswordKey(passwordKey);
        String password = userDetails.getPassword();
        String passencrypt = bCryptPasswordEncoder.encode(password);
        SendMail newEmail = new SendMail(user.getUsername(), "Password", "Your password for the account is \\r\\n Login : " + userDetails.getUsername() +" \\r\\n password is: " + password);
        newEmail.sendMailText(user.getUsername(), "Password", "Your password for the account is Login : " + userDetails.getUsername() +" and the password is: " + password);
        userDetails.setPassword(passencrypt);
        User newUser = userRepository.save(userDetails);
        return newUser;
    }
}
