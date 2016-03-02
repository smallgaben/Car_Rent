package model.DAO;

import model.entities.User;

import java.util.Set;

public interface UserDAO {
    User add(User user);

    User readById(int id);

    User readByName(String name);

    Set<User> readAll();

    User update(int id);

    void delete(int id);

}
