package nl.qien.uren.repository;

import nl.qien.uren.controller.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();

    int save(Employee employee);

    Optional<Employee> findById(Long id);


/*
    int update(Employee book);

    int deleteById(Long id);

    List<Employee> findAll();

    List<Employee> findByNameAndPrice(String name, BigDecimal price);


    String getNameById(Long id);
*/
}

