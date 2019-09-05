package nl.qien.uren.repository;

import nl.qien.uren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //public User findByEmailadress(String emailAddress);


    User findByEmailadress(String emailadress);
    User findByPassword(String password);
}
