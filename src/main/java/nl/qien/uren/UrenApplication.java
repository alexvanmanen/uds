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


        Customer customer = new Customer();
        customer.setName("Ajax");

        Project project = new Project();
        project.setName("project van Ajax");

        //HIER ZET IK DE CUSTOMER OP HET PROJECT
        project.setCustomer(customer);

        //HIER KOPPEL IK DE LIJST MET PROJECTEN AAN DE CUSTOMER
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        customer.setProjects(projects);

        customerRepository.save(customer);
        projectRepository.save(project);


        //        Customer customer3 = new Customer();
//        customer3.setName("Ajax Amsterdam");
//        customer3.setAddress("hhahl");
//        customerRepository.save(customer3);
//
//
//
//
//        Customer customerA = new Customer();
//        Customer customerB = new Customer();
//        customerA.setName("Customer A");
//        customerB.setName("Customer B");
//
//
//        Set<Project> projectsA = new HashSet<>();
//        Project projectA1 = new Project("Project A1", customerA);
//        projectsA.add(projectA1);
//        customerA.setProjects(projectsA);
//
//
//        Set<Project> projectsB = new HashSet<>();
//        projectsB.add(new Project("Project B3", customerB));
//        projectsB.add(new Project("Project B4", customerB));
//        customerB.setProjects(projectsB);
//
//
//        customerRepository.saveAll(Arrays.asList(customerA, customerB));
//        projectRepository.saveAll(projectsA);
//        projectRepository.saveAll(projectsB);


        //show the info
//        for (Customer customer : customerRepository.findAll()) {
//            System.out.println(customer);
//        }
    }


}
