package model.DAO;

import model.entities.Order;

public interface OrderDAO {
    Order create(Order order);
    Order readById(int id);
    Order readByName(String name);
    Order update(int id);
    void delete(int id);
}
