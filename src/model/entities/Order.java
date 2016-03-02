package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = -3208081497655704033L;

    private int id;
    private String passport;
    private User user;
    private Car car;
    private Date startDate;
    private Date finishDate;
    private boolean driver;
    private Status status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (driver != order.driver) return false;
        if (!passport.equals(order.passport)) return false;
        if (!user.equals(order.user)) return false;
        if (!car.equals(order.car)) return false;
        if (!startDate.equals(order.startDate)) return false;
        if (!finishDate.equals(order.finishDate)) return false;
        return status.equals(order.status);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + passport.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + (driver ? 1 : 0);
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", passport='" + passport + '\'' +
                ", user=" + user +
                ", car=" + car +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", driver=" + driver +
                ", status=" + status +
                '}';
    }
}
