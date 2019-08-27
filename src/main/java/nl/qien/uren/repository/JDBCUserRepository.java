package nl.qien.uren.repository;

import nl.qien.uren.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count(){
        return jdbcTemplate
                            .queryForObject("select count(*) from User", Integer.class);
    }

    @Override
    public int save(User user){
        return jdbcTemplate.update("insert into User(ID, emailadress, password, active, firstname, lastname, straat, huisnummer, postcode, woonplaats, telefoonnummer, bankrekeningnr, firstlogin) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                user.getId(), user.getEmailadress(), user.getPassword(), user.getActive(),user.getFirstname(), user.getLastname(), user.getStraat(), user.getHuisnummer(), user.getPostcode(), user.getWoonplaats(),
                user.getTelefoonnummer(), user.getBankrekeningnr(), user.getFirstlogin());

    }

    @Override
    public List<User> findAll(){
        return jdbcTemplate.query(
                "select * from User",
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getBoolean("active"),
                                rs.getString("emailadress"),
                                rs.getString("password"),
                                rs.getString("straat"),
                                rs.getString("huisnummer"),
                                rs.getString("postcode"),
                                rs.getString("woonplaats"),
                                rs.getInt("telefoonnummer"),
                                rs.getString("bankrekeningnr"),
                                rs.getBoolean("firstlogin")
                        )
        );
    }

    @Override
    public Boolean validateUser(String email, String password) {
        String sqlQuery = "select count(*) from User where emailadress=? and password=?";
        Object[] arrayWithParameters = {email, password};
        int count = jdbcTemplate.queryForObject(sqlQuery, arrayWithParameters, Integer.class);
        return count == 1 ? true: false;
    }

    @Override
    public Long getMaxId(){
        return jdbcTemplate
                .queryForObject("select max(id) from User", Long.class) +1;
    }
}
