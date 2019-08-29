package nl.qien.uren;

import nl.qien.uren.entity.Customer;
import nl.qien.uren.entity.Project;
import nl.qien.uren.repository.CustomerRepository;
import nl.qien.uren.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class UrenApplication  implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(UrenApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {


        Customer customerA = new Customer();
        customerA.setName("Customer A");

        Customer customerB = new Customer();
        customerB.setName("Customer B");

        Project projectA1 = new Project();
        projectA1.setName("Project A1");

        Project projectA2 = new Project();
        projectA2.setName("Project A2");


        Project projectB1 = new Project();
        projectB1.setName("Project B1");

        Project projectB2 = new Project();
        projectB2.setName("Project B2");

        Set<Project> projectsA = new HashSet<>();
        projectsA.add(projectA1);
        projectsA.add(projectA2);


        Set<Project> projectsB = new HashSet<>();
        projectsB.add(projectB1);
        projectsB.add(projectB2);

        customerA.setProjects(projectsA);
        customerB.setProjects(projectsB);

        customerRepository.saveAll(Arrays.asList(customerA,customerB));
        projectRepository.saveAll(Arrays.asList(projectA1,projectA2,projectB1,projectB2));


        //show the info
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
    }


}
