package nl.qien.uren.repository;


import nl.qien.uren.controller.UrenRegistratie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCUrenRegistratieRepository implements UrenRegistratieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from Uren_Registratie", Integer.class);
    }

    @Override
    public int save(UrenRegistratie urenRegistratie) {
        return jdbcTemplate.update(
                "insert into Uren_Registratie (EMPLOYEE_ID, PROJECT_ID, AANTAL_UREN) values(?,?,?)",
                urenRegistratie.getEmployeeId(),urenRegistratie.getProjectId(),urenRegistratie.getAantalUren());
    }

}
