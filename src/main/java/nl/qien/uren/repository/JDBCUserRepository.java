package nl.qien.uren.repository;

import nl.qien.uren.controller.User;
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
        return jdbcTemplate.update("insert into User(ID, email, password) values(?,?,?)", user.getId(), user.getEmailAdress(), user.getPassword());

    }

    @Override
    public List<User> findAll(){
        return jdbcTemplate.query(
                "select * from User",
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    @Override
    public Boolean validateUser(String email, String password) {
        String sqlQuery = "select count(*) from User where email=? and password=?";
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
