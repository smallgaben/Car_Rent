package model.DAO;

import model.entities.Car;

import java.util.Set;

public interface CarDAO {
    Car create(Car car);

    Car readById(int id);

    Set<Car> readAll();

    void update(Car car);

    void delete(int id);
}
