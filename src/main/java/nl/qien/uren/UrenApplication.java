package nl.qien.uren;

import nl.qien.uren.entity.Customer;
import nl.qien.uren.entity.Project;
import nl.qien.uren.repository.CustomerRepository;
import nl.qien.uren.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class UrenApplication implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProjectRepository projectRepository;


    public static void main(String[] args) {
        SpringApplication.run(UrenApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String...strings) throws Exception{
        System.out.println("hallo");
//    Customer customerA = new Customer();
//    Customer customerB = new Customer();
//    customerA.setName("AJAX AMSTERDAM");
//    customerB.setName("TOTTENHAM HOTSPUR");
//
//    Project projectX = new Project();
//    Project projectXX = new Project();
//
//    projectX.setProjectName("Champions League");
//    projectXX.setProjectName("Finale CL");
//
//    Project projectY = new Project();
//    Project projectYY = new Project();
//
//    projectY.setProjectName("Europa League");
//    projectYY.setProjectName("Finale EL");

//    Set<Project> projectsA = new HashSet<>();
//    projectsA.add(projectX);
//    projectsA.add(projectXX);
//
//    Set<Project> projectsB = new HashSet<>();
//    projectsB.add(projectY);
//    projectsB.add(projectYY);

//    customerA.setProjects(projectsA);
//    customerB.setProjects(projectsB);
//
//
//   customerRepository.save(customerA);
//   customerRepository.save(customerB);
//
//
//   projectRepository.save(projectX);
//   projectRepository.save(projectXX);
//   projectRepository.save(projectY);
//   projectRepository.save(projectYY);
//    List<Project> projectsA = new ArrayList<>();
//    projectsA.add(projectX);
//    projectsA.add(projectXX);
//
//    customerRepository.save(customerA);
//    projectRepository.saveAll(projectsA);
//
//
//
//        Customer bart = new Customer();
//        bart.setName("Irritante gast");
//
//        Project killBart = new Project();
//        killBart.setProjectName("RIP");
//
//        Project makeBartRich = new Project();
//        makeBartRich.setProjectName("moneyManBartoh");
//
//        Set<Project> projectsBart = new HashSet<>();
//        projectsBart.add(killBart);
//        projectsBart.add(makeBartRich);
//
//        bart.setProjects(projectsBart);
//        killBart.setCustomer(bart);
//
//        makeBartRich.setCustomer(bart);
//
//        customerRepository.save(bart);
//        projectRepository.save(killBart);
//        projectRepository.save(makeBartRich);

        Customer alex = new Customer();
        alex.setName("Alex");

        Project SQL = new Project();
        Project MSN = new Project();


        MSN.setCustomer(alex);
        SQL.setCustomer(alex);

        Set<Project> projects = new HashSet<>();
        projects.add(SQL);
        projects.add(MSN);

        alex.setProjects(projects);

        customerRepository.save(alex);
        projectRepository.saveAll(projects);

    }

}
