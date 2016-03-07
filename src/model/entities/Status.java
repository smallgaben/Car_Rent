package model.entities;

import java.io.Serializable;
import java.util.Set;

public class Status implements Serializable {
    private static final long serialVersionUID = -1121205141512192760L;
    public static final int DEFAULT_CHECK_STATUS=4;
    public static final int PAID_CHECK_STATUS=5;
    public static final int DEFAULT_CAR_STATUS=0;
    public static final int DISABLED_CAR_STATUS=1;
    public static final int RENT_ORDER_STATUS=2;
    public static final int RETURN_ORDER_STATUS=9;
    public static final int REPAIR_ORDER_STATUS=3;
    public static final int CANCELED_CHECK_STATUS=6;
    public static final int ACCEPTED_CHECK_STATUS=7;
    public static final int SUCCESS_CHECK_STATUS=8;


    private int id;
    private String name;

    private Set<Car> cars;

    private Set<Order> orders;

    private Set<Check> checks;

    public Set<Check> getChecks() {
        return checks;
    }

    public void setChecks(Set<Check> checks) {
        this.checks = checks;
    }

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
                ", checks=" + checks +
                '}';
    }
}
