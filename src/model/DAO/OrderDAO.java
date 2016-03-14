package model.DAO;

import model.entities.Order;

import java.util.Set;

public interface OrderDAO {
    Order create(Order order);

    Order readById(int id);

    Set<Order> readByCheck(int id);

    Set<Order> readAll();

    void update(Order order);

    void delete(int id);
}
