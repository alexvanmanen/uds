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
        return jdbcTemplate.update("insert into User(ID, emailadress, password, active, firstname, lastname, street, housenumber, zipcode, city, phonenumber, accountnumber, firstlogin) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                user.getId(), user.getEmailadress(), user.getPassword(), user.getActive(),user.getFirstname(), user.getLastname(), user.getStreet(), user.getHousenumber(), user.getZipcode(), user.getCity(),
                user.getPhonenumber(), user.getAccountnumber(), user.getFirstlogin());

    }

    @Override
    public List<User> findAll(){
        return jdbcTemplate.query(
                "select * from User",
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getBoolean("active"),
                                rs.getString("emailadress"),
                                rs.getString("password"),
                                rs.getString("street"),
                                rs.getString("housenumber"),
                                rs.getString("zipcode"),
                                rs.getString("city"),
                                rs.getInt("phonenumber"),
                                rs.getString("accountnumber"),
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
    public int getMaxId(){
        return jdbcTemplate
                .queryForObject("select max(id) from User", Integer.class) +1;
    }
}
