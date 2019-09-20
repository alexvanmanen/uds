package nl.qien.uren.controller;

import nl.qien.uren.entity.Project;
import nl.qien.uren.repository.ProjectRepository;
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

    @GetMapping("/admin/manageproject")
    public String manageproject(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        model.addAttribute("name", name);
        return "admin/manageproject";
    }

    @GetMapping("/admin/viewproject")
    public String viewproject(@RequestParam(name="name", required=false, defaultValue="wereld") String name, Model model) {
        Project project = projectRepository.findById(1);
        model.addAttribute("name", name);
        model.addAttribute("email", project.getEmail());
        return "viewproject";
    }
}
