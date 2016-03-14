package model.DAOImp;

import model.DAO.*;
import model.DBUtil.DSHolder;
import model.entities.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OrderDAOImp implements OrderDAO {
    private static final Logger logger = Logger.getLogger(OrderDAOImp.class);

    @Override
    public Order create(Order order) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO Orders(passport, user_id, car, startdate, finishdate, driver, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getPassport());
            ps.setInt(2, order.getUser().getId());
            ps.setInt(3, order.getCar().getId());
            ps.setDate(4, order.getStartDate());
            ps.setDate(5, order.getFinishDate());
            ps.setBoolean(6, order.isDriver());
            ps.setInt(7, order.getStatus().getId());
            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                logger.info("Created Order with id: " + id);
                order.setId(id);
            }
        } catch (SQLException e) {
            logger.error("Can't add order " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        } finally {
            DSHolder.close(ps);
            DSHolder.close(connection);
            DSHolder.close(resultSet);
        }
        return order;
    }

    @Override
    public Order readById(int id) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;

        Order order = null;
        String sql = "SELECT *FROM Orders WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                order = executeOrder(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't read Order " + e);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return order;
    }

    @Override
    public Set<Order> readByCheck(int id) {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resultSet = null;

        HashSet<Order> orders = new HashSet<>();
        String sql = "SELECT *FROM Orders WHERE check_id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                orders.add(executeOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Can't read Order " + e);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return orders;
    }

    @Override
    public Set<Order> readAll() {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM Orders";
        Set<Order> orders = null;
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            orders = new HashSet<>();
            while (resultSet.next()) {
                orders.add(executeOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Can't read all Orders");
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        PreparedStatement ps = null;
        Connection connection = null;

        String sql = "UPDATE Orders Set passport=?, user_id=?, check_id=?, car=?, startdate=?, finishdate=?, driver=?, status=? WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, order.getPassport());
            ps.setInt(2, order.getUser().getId());
            ps.setInt(3, order.getCheck().getId());
            ps.setInt(4, order.getCar().getId());
            ps.setDate(5, new Date(order.getStartDate().getTime()));
            ps.setDate(6, new Date(order.getFinishDate().getTime()));
            ps.setBoolean(7, order.isDriver());
            ps.setInt(8, order.getStatus().getId());
            ps.setInt(9, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update the car " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }

    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;
        Connection connection = null;

        String sql = "DELETE FROM Orders WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete Order " + e);
            DSHolder.rollback(connection);
        } finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }


    /**
     * This method deletes all orders that is connect with car need to delete
     *
     * @param id the id of car
     */
    public void deleteByCarId(int id) {
        PreparedStatement ps = null;
        Connection connection = null;
        String sql = "DELETE FROM Orders WHERE car=?";

        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Can't delete Order " + e);
            DSHolder.rollback(connection);
        } finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }

    }

    public Order executeOrder(ResultSet resultSet) throws SQLException {
        CarDAO carDAOImp = new CarDAOImp();
        StatusDAO statusDAOImp = new StatusDAOImp();
        UserDAO userDAOImp = new UserDAOImp();
        CheckDAO checkDAO = new CheckDAOImp();
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setPassport(resultSet.getString(2));
        order.setUser(userDAOImp.readById(resultSet.getInt(3)));
        order.setCheck(checkDAO.read(resultSet.getInt(4)));
        order.setCar(carDAOImp.readById(resultSet.getInt(5)));
        order.setStartDate(resultSet.getDate(6));
        order.setFinishDate(resultSet.getDate(7));
        order.setDriver(resultSet.getBoolean(8));
        order.setStatus(statusDAOImp.read(resultSet.getInt(9)));

        return order;
    }
}
