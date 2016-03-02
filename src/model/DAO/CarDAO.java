package model.DAO;

import model.entities.Car;

import java.util.Set;

public interface CarDAO {
    Car create(Car car);

    Car readById(int id);

    Car readByName(String name);

    Set<Car> readAll();

    Car update(int id);

    void delete(int id);
}
