package model.entities;

import java.io.Serializable;
import java.util.Set;

public class Status implements Serializable {
    private static final long serialVersionUID = -1121205141512192760L;

    private int id;
    private String name;

    private Set<Car> cars;

    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                ", orders=" + orders +
                '}';
    }
}
