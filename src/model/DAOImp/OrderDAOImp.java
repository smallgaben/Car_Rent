package model.DAOImp;

import model.DAO.OrderDAO;
import model.DBUtil.DSHolder;
import model.entities.Check;
import model.entities.Order;
import model.entities.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OrderDAOImp implements OrderDAO {
    private static final Logger logger=Logger.getLogger(OrderDAOImp.class);
    Connection connection=null;
    Statement statement=null;
    PreparedStatement ps=null;
    ResultSet resultSet=null;
    @Override
    public Order create(Order order) {
        String sql="INSERT INTO Orders(passport, user_id, car, startdate, finishdate, driver, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        CheckDAOImp checkDAOImp=new CheckDAOImp();
        try{
            connection = DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getPassport());
            ps.setInt(2, order.getUser().getId());
            ps.setInt(3,order.getCar().getId());
            ps.setDate(4, new Date(order.getStartDate().getTime()) );
            ps.setDate(5, new Date(order.getFinishDate().getTime()));
            ps.executeUpdate();

            resultSet=ps.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                logger.debug("Created Order with id: "+id);
                order.setId(id);
                checkDAOImp.create(createCheck(id));
            }
        }catch (SQLException e){
            logger.error("Can't add order "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
        }

        return order;

    }

    @Override
    public Order readById(int id) {
        Order order=null;
        String sql="SELECT *FROM Orders WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                order=executeOrder(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read Order "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return order;
    }


    @Override
    public Order readByName(String name) {
        Order order=null;
        String sql="SELECT *FROM Orders WHERE name=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, name);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                order=executeOrder(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read Order "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return order;
    }

    @Override
    public Set<Order> readAll() {
        String sql="SELECT * FROM Orders";
        Set<Order> orders=null;
        try{
            statement=DSHolder.getInstance().getConnection().createStatement();
            resultSet=statement.executeQuery(sql);
            orders= new HashSet<>();
            while(resultSet.next()){
                orders.add(executeOrder(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Orders");
            e.printStackTrace();
        }finally {
            DSHolder.close(connection, statement, resultSet);
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        String sql="UPDATE Orders Set passport=?, user_id=?, car=?, startdate=?, finishdate=?, driver=?, status=? WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, order.getPassport());
            ps.setInt(2, order.getUser().getId());
            ps.setInt(3, order.getCar().getId());
            ps.setDate(4, new Date(order.getStartDate().getTime()));
            ps.setDate(5, new Date(order.getFinishDate().getTime()));
            ps.setBoolean(6, order.isDriver());
            ps.setInt(7, order.getStatus().getId());
            ps.setInt(8,order.getId());
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
        String sql="DELETE FROM Orders WHERE id=?";
        CheckDAOImp checkDAOImp=new CheckDAOImp();
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            checkDAOImp.delete(id);
        }catch (SQLException e){
            logger.error("Can't delete Order "+ e);
            DSHolder.rollback(connection);
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }

    }

    public void deleteByCarId(int id){
        String sql="DELETE FROM Orders WHERE car=?";
        CheckDAOImp checkDAOImp=new CheckDAOImp();
        try{
            Set<Integer> del=getDeletingIds(id);
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeQuery();
            for(Integer i: del){
                checkDAOImp.delete(i);
            }
        }catch (SQLException e){
            logger.error("Can't delete Order "+ e);
            DSHolder.rollback(connection);
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }

    }

    public Order executeOrder(ResultSet resultSet) throws SQLException{
        CarDAOImp carDAOImp=new CarDAOImp();
        StatusDAOImp statusDAOImp=new StatusDAOImp();
        UserDAOImp userDAOImp=new UserDAOImp();
        Order order=new Order();
        order.setId(resultSet.getInt(1));
        order.setPassport(resultSet.getString(2));
        order.setUser(userDAOImp.readById(resultSet.getInt(3)));
        order.setCar(carDAOImp.readById(resultSet.getInt(4)));
        order.setStartDate(resultSet.getDate(5));
        order.setFinishDate(resultSet.getDate(6));
        order.setDriver(resultSet.getBoolean(7));
        order.setStatus(statusDAOImp.read(resultSet.getInt(8)));

        return order;
    }

    public Check createCheck(int id){
        StatusDAOImp statusDAOImp=new StatusDAOImp();
        Check check = new Check();
        check.setDate(new Date(System.currentTimeMillis()));
        check.setOrder(readById(id));
        check.setPrice(Check.DEFAULT_PRICE);
        check.setDescription("Add description");
        check.setStatus(statusDAOImp.read(Status.DEFAULT_CHECK_STATUS));
        return check;
    }

    public Set<Integer> getDeletingIds(int id) throws SQLException{
        String sql="SELECT *FROM Orders WHERE car=?";
        ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1,id);
        resultSet = ps.executeQuery();
        Set<Integer>integers=new HashSet<>();
        while (resultSet.next()){
            integers.add(resultSet.getInt(1));
        }
        return integers;
    }
}
