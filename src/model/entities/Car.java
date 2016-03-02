package model.entities;

import java.io.Serializable;
import java.util.Set;


public class Car implements Serializable {
    private static final long serialVersionUID = -6486130755661948362L;

    private int id;
    private String mark;
    private String name;
    private int cost;
    private Class carClass;
    private Status status;
    private Set<Order> orders;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Class getCarClass() {
        return carClass;
    }

    public void setCarClass(Class carClass) {
        this.carClass = carClass;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (cost != car.cost) return false;
        if (!mark.equals(car.mark)) return false;
        if (!name.equals(car.name)) return false;
        if (!carClass.equals(car.carClass)) return false;
        if (!status.equals(car.status)) return false;
        return !(orders != null ? !orders.equals(car.orders) : car.orders != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + cost;
        result = 31 * result + carClass.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", carClass=" + carClass +
                ", status=" + status +
                '}';
    }
}
