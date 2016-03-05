package model.entities;

import java.io.Serializable;
import java.sql.Date;

public class Check implements Serializable {
    private static final long serialVersionUID = 5333068964774944352L;
    public static final String ADD_CHECK_DESCR="Waiting to be accepted by Manager";

    private int id;
    private Date date;
    private String description;
    private int price;
    private Order order;
    private Status status;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //One To One

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

        Check check = (Check) o;

        if (id != check.id) return false;
        if (price != check.price) return false;
        if (!date.equals(check.date)) return false;
        return description.equals(check.description) && order.equals(check.order);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price;
        result = 31 * result + order.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +"\n"+
                ", date=" + date +"\n"+
                ", description='" + description + '\'' +"\n"+
                ", price=" + price +"\n"+
                ", order=" + order +"\n"+
                '}';
    }
}
