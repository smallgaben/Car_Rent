package model.DAO;

import java.util.Set;

import model.entities.Class;

public interface ClassDAO {
    Class create(Class addClass);

    Class read(int id);

    Set<Class> readAll();

    void update(Class carClass);

    void delete(int id);
}
