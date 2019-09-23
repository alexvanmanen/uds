package nl.qien.uren.controller;

import nl.qien.uren.entity.Project;
import nl.qien.uren.entity.User;
import nl.qien.uren.repository.ProjectRepository;
import nl.qien.uren.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProjectController {

   @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/admin/manageproject")
    public String manageproject(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("name", user.getFirstname());
        return "admin/manageproject";
    }

    @GetMapping("/admin/viewproject")
    public String viewproject(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        Project project = projectRepository.findById(1);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("name", user.getFirstname());
        model.addAttribute("email", project.getEmail());
        return "viewproject";
    }
}
