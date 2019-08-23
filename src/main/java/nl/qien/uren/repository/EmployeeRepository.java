package nl.qien.uren.repository;

import nl.qien.uren.controller.Employee;
import nl.qien.uren.controller.UrenRegistratie;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();

    int save(Employee employee);

    List<Employee> findAll();

    Integer findById(Long id);

    int saveUren(UrenRegistratie uren);


/*
    int update(Employee book);

    int deleteById(Long id);

    List<Employee> findAll();

    List<Employee> findByNameAndPrice(String name, BigDecimal price);


    String getNameById(Long id);
*/
}

