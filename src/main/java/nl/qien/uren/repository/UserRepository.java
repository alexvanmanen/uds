package nl.qien.uren.repository;

import nl.qien.uren.model.user.User;

import java.util.List;

public interface UserRepository {
    int count();

    int save(User user);

    Boolean validateUser(String email, String password);

    List<User> findAll();

    int getMaxId();
}
