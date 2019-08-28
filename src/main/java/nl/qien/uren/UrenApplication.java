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
        // save a couple of categories
        Customer customer1 = new Customer("Customer A");


//        Set<Project> projects = new HashSet<>();
//        customer1.addProject();
//        customer1.addProject(new Project("Project A2", customer1));
//        customer1.addProject(new Project("Project A3", customer1));


        customerRepository.saveAll(Arrays.asList(customer1));

        List<Project> projects = Arrays.asList(new Project("Project A1", customer1), new Project("Project A2", customer1));
        projectRepository.saveAll(projects);



        // fetch all categories
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
    }


}
