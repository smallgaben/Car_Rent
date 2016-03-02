package model.DAO;

import model.entities.User;

import java.util.Set;

public interface UserDAO {
    User create(User user);

    User readById(int id);

    User readByName(String name);

    Set<User> readAll();

    void update(User user);

    void delete(int id);

}
