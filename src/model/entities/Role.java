package model.entities;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
    private static final long serialVersionUID = 1745541598538260557L;
    public static final int DEFAULT_ROLE=2;

    private int id;
    private String name;

    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
