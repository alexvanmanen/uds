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
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");

        Set<Project> projectsA = new HashSet<>();
        projectsA.add(new Project("Project A1", customerA));
        customerA.setProjects(projectsA);


        Set<Project> projectsB = new HashSet<>();
        projectsB.add(new Project("Project B3", customerB));
        projectsB.add(new Project("Project B4", customerB));
        customerB.setProjects(projectsB);


        customerRepository.saveAll(Arrays.asList(customerA, customerB));
        projectRepository.saveAll(projectsA);
        projectRepository.saveAll(projectsB);


        //show the info
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
    }


}
