package at.fhv.se.platform.domain.port.outbound.persistence;

import at.fhv.se.platform.domain.model.User;

import java.util.List;

/**
 * @author Justin Ströhle
 * 16.11.2023
 */

public interface UserRepository {
    void save(User user);
    List<User> getAllUsers();
    User getUser(String id);
}