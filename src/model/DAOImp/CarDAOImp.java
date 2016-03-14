package model.DAOImp;

import model.DAO.CarDAO;
import model.DAO.ClassDAO;
import model.DAO.StatusDAO;
import model.DBUtil.DSHolder;
import model.entities.Car;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CarDAOImp implements CarDAO {
    private static final Logger logger = Logger.getLogger(CarDAOImp.class);

    @Override
    public Car create(Car car) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO Cars(mark,name,cost,class,status) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getMark());
            ps.setString(2, car.getName());
            ps.setInt(3, car.getCost());
            ps.setInt(4, car.getCarClass().getId());
            ps.setInt(5, car.getStatus().getId());
            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                logger.debug("Created car with id: " + id);
                car.setId(id);
            }
        } catch (SQLException e) {
            logger.error("Can't add car " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }

        return car;
    }

    @Override
    public Car readById(int id) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Car car = null;
        String sql = "SELECT *FROM Cars WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                car = executeCar(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't read Car " + e);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return car;
    }

    @Override
    public Set<Car> readAll() {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String sql = "SELECT *FROM Cars";
        Set<Car> cars = null;
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            cars = new HashSet<>();
            while (resultSet.next()) {
                cars.add(executeCar(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Can't read all Cars");
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return cars;
    }

    @Override
    public void update(Car car) {
        PreparedStatement ps = null;
        Connection connection = null;
        String sql = "UPDATE Cars Set mark=?, name=?, cost=?, class=?, status=? WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, car.getMark());
            ps.setString(2, car.getName());
            ps.setInt(3, car.getCost());
            ps.setInt(4, car.getCarClass().getId());
            ps.setInt(5, car.getStatus().getId());
            ps.setInt(6, car.getId());
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

        String sql = "DELETE FROM Cars WHERE id=?";
        OrderDAOImp orderDAOImp = new OrderDAOImp();
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            orderDAOImp.deleteByCarId(id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete Car " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    public Car executeCar(ResultSet resultSet) throws SQLException {
        StatusDAO statusDAOImp = new StatusDAOImp();
        ClassDAO classDAOImp = new ClassDAOImp();
        Car car = new Car();
        car.setId(resultSet.getInt(1));
        car.setMark(resultSet.getString(2));
        car.setName(resultSet.getString(3));
        car.setCost(resultSet.getInt(4));
        car.setCarClass(classDAOImp.read(resultSet.getInt(5)));
        car.setStatus(statusDAOImp.read(resultSet.getInt(6)));
        return car;
    }
}
