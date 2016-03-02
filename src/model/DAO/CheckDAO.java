package model.DAO;

import model.entities.Check;

import java.util.Set;

public interface CheckDAO {
    Check create(Check check);
    Check readByOrderId(int id);
    Set<Check> readAll();
    void update(Check check);
    void delete(int id);
}
