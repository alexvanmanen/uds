package nl.qien.uren.repository;

import nl.qien.uren.controller.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCEmployeeRepository implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from Employee", Integer.class);
    }


    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(
                "insert into Employee (ID, FIRST_NAME, LAST_NAME) values(?,?,?)",
                employee.getId(), employee.getFirstName(), employee.getLastName());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from Employee",
                (rs, rowNum) ->
                        new Employee(
                                rs.getLong("ID"),
                                rs.getString("FIRST_NAME"),
                                rs.getString("LAST_NAME")
                        )
        );
    }



    /*
    @Override
    public int update(Employee Employee) {
        return jdbcTemplate.update(
                "update Employee set name = ? where id = ?",
                Employee.getname(), Employee.getId());
    }


    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete Employee where id = ?",
                id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from Employee",
                (rs, rowNum) ->
                        new Employee(
                                rs.getLong("id"),
                                rs.getString("firstName"),
                                rs.getString("lastName")
                        )
        );
    }


    @Override
    public List<Employee> findByNameAndname(String name, BigDecimal name) {
        return jdbcTemplate.query(
                "select * from Employee where name like ? and name <= ?",
                new Object[]{"%" + name + "%", name},
                (rs, rowNum) ->
                        new Employee(
                                rs.getLong("id"),
                                rs.getString("name"),
                        )
        );
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select name from Employee where id = ?",
                new Object[]{id},
                String.class
        );
    }
    */
}

