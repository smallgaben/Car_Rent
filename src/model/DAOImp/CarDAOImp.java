package model.DAOImp;

import model.DAO.CarDAO;
import model.DBUtil.DSHolder;
import model.entities.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CarDAOImp implements CarDAO {
    private static final Logger logger=Logger.getLogger(CarDAOImp.class);
    Connection connection=null;
    Statement statement=null;
    PreparedStatement ps=null;
    ResultSet resultSet=null;
    @Override
    public Car create(Car car) {
        String sql="INSERT INTO Cars(mark,name,cost,class,status) VALUES (?, ?, ?, ?, ?)";
        try{
            connection = DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,car.getMark());
            ps.setString(2,car.getName());
            ps.setInt(3,car.getCost());
            ps.setInt(4, car.getCarClass().getId());
            ps.setInt(5,car.getStatus().getId());
            ps.executeUpdate();

            resultSet=ps.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                logger.debug("Created car with id: "+id);
                car.setId(id);
            }
        }catch (SQLException e){
            logger.error("Can't add car "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
        }

        return car;
    }

    @Override
    public Car readById(int id) {
        Car car=null;
        String sql="SELECT FROM Cars WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                car=executeCar(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read Car "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return car;
    }

    @Override
    public Car readByName(String name) {
        Car car=null;
        String sql="SELECT FROM Cars WHERE name=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, name);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                car=executeCar(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read Car "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return car;
    }

    @Override
    public Set<Car> readAll() {
        String sql="SELECT * FROM Cars";
        Set<Car> cars=null;
        try{
            statement=DSHolder.getInstance().getConnection().createStatement();
            statement.executeQuery(sql);
            cars = new HashSet<>();
            while(resultSet.next()){
                cars.add(executeCar(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Cars");
            e.printStackTrace();
        }finally {
            DSHolder.close(connection, statement, resultSet);
        }
        return cars;
    }

    @Override
    public void update(Car car) {
        String sql="UPDATE Cars Set mark=?, name=?, cost=?, class=?, status=? WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, car.getMark());
            ps.setString(2, car.getName());
            ps.setInt(3,car.getCost());
            ps.setInt(4, car.getCarClass().getId());
            ps.setInt(5, car.getStatus().getId());
            ps.setInt(6, car.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update the car "+ e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
    }

    @Override
    public void delete(int id) {
        String sql="DELETE FROM Cars WHERE id=?";
        OrderDAOImp orderDAOImp=new OrderDAOImp();
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            orderDAOImp.deleteByCarId(id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Car "+ e);
            DSHolder.rollback(connection);
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
    }

    public Car executeCar(ResultSet resultSet) throws SQLException{
        StatusDAOImp statusDAOImp=new StatusDAOImp();
        ClassDAOImp classDAOImp=new ClassDAOImp();
        Car car=new Car();
        car.setId(resultSet.getInt(1));
        car.setMark(resultSet.getString(2));
        car.setName(resultSet.getString(3));
        car.setCost(resultSet.getInt(4));
        car.setCarClass(classDAOImp.read(resultSet.getInt(5)));
        car.setStatus(statusDAOImp.read(resultSet.getInt(6)));
        return  car;
    }
}
