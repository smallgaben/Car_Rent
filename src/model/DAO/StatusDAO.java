package model.DAO;

import model.entities.Status;

import java.util.Set;

public interface StatusDAO {
    Status create(Status status);

    Status read(int id);

    Set<Status> readAll();

    void update(Status status);

    void delete(int id);
}
