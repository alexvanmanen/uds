package nl.qien.uren.service;

import nl.qien.uren.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
public User findUserByEmail(String email) ;
public User saveUser(User user);
}