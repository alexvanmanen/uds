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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void run(String... strings) throws Exception {
        System.out.println("hallo");
//        Customer customer = new Customer(project,1,"robin","wageningen");
//        Customer customer2 = new Customer(project2,848,"shoko", "Dublin");
        Customer customerA = new Customer();

        customerA.setNaam("customerA");
//        customerRepository.save(customer);
//        customerRepository.save(customer2);

        Set<Project> projectsA = new HashSet<>();
        Project projectA1 = new Project();
        projectA1.setCustomer(customerA);
        projectA1.setProjectName("robin");
        projectsA.add(projectA1);
        customerRepository.save(customerA);
        projectRepository.save(projectA1);
        //projectRepository.saveAll(projectsA);


//        Project project = new Project(customer,1,"applicatie", LocalDate.of(2009,9,19));
//        Project project2 = new Project(customer2,2,"applicatie2",LocalDate.of(1993,9,19));
//        projectRepository.save(project);
//        projectRepository.save(project2);
    }
}
