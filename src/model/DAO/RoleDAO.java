package model.DAO;

import model.entities.Role;

import java.util.Set;

public interface RoleDAO {
    Role create(Role role);

    Role read(int id);

    Set<Role> readAll();

    void update(Role role);

    void delete(int id);
}
