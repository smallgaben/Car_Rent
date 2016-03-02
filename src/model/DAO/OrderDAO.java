package model.DAO;

import model.entities.Order;

import java.util.Set;

public interface OrderDAO {
    Order create(Order order);
    Order readById(int id);
    Order readByName(String name);
    Set<Order> readAll();
    void update(Order order);
    void delete(int id);
}
