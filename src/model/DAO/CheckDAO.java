package model.DAO;

import model.entities.Check;

import java.util.Set;

public interface CheckDAO {
    Check create(Check check);
    Check readById(int id);
    Check readByName(String name);
    Set<Check> readAll();
    Check update(int id);
    void delete(int id);
}
