package nl.qien.uren.repository;

import nl.qien.uren.model.user.Employee;

import java.util.List;

public interface EmployeeRepository {

    int count();

    int save(Employee employee);

    List<Employee> findAll();



/*
    int update(Employee book);

    int deleteById(Long id);

    List<Employee> findAll();

    List<Employee> findByNameAndPrice(String name, BigDecimal price);


    String getNameById(Long id);
*/
}

