package model.DAOImp;

import model.DAO.CheckDAO;
import model.DAO.OrderDAO;
import model.DAO.StatusDAO;
import model.DBUtil.DSHolder;
import model.entities.Check;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CheckDAOImp implements CheckDAO {
    private static final Logger logger=Logger.getLogger(OrderDAOImp.class);

    @Override
    public Check create(Check check) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="INSERT INTO Checks(date, description, order_id, price, status ) VALUES (?, ?, ?, ?, ?)";
        try{
            connection = DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, check.getDate());
            ps.setString(2, check.getDescription());
            ps.setInt(3,check.getOrder().getId());
            ps.setInt(4, check.getPrice());
            ps.setInt(5, check.getStatus().getId());
            ps.executeUpdate();

            resultSet=ps.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                logger.debug("Created Check with id: "+id);
                check.setId(id);
            }
        }catch (SQLException e){
            logger.error("Can't add Check "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(ps);
            DSHolder.close(connection);
            DSHolder.close(resultSet);
        }

        return check;
    }

    @Override
    public Check readByOrderId(int id) {
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Connection connection=null;
        Check check=null;
        String sql="SELECT *FROM Checks WHERE order_id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                check=executeCheck(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read Check "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return check;
    }

    @Override
    public Set<Check> readAll() {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="SELECT * FROM Checks";
        Set<Check> checks=null;
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            resultSet=ps.executeQuery();
            checks = new HashSet<>();
            while(resultSet.next()){
                checks.add(executeCheck(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Checks");
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return checks;
    }

    @Override
    public void update(Check check) {
        PreparedStatement ps=null;
        Connection connection=null;
        String sql="UPDATE Checks Set date=?, description=?, price=?, status=? WHERE order_id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setDate(1, new Date( check.getDate().getTime()));
            ps.setString(2, check.getDescription());
            ps.setInt(3, check.getPrice());
            ps.setInt(4, check.getStatus().getId());
            ps.setInt(5, check.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update the Check "+ e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement ps=null;
        Connection connection=null;

        String sql="DELETE FROM Checks WHERE order_id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Check "+ e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    public Check executeCheck(ResultSet resultSet)throws SQLException{
        StatusDAO statusDAOImp=new StatusDAOImp();
        OrderDAO orderDAOImp=new OrderDAOImp();
        Check check=new Check();
        check.setId(resultSet.getInt(1));
        check.setDate(resultSet.getDate(2));
        check.setDescription(resultSet.getString(3));
        check.setOrder(orderDAOImp.readById(resultSet.getInt(4)));
        check.setPrice(resultSet.getInt(5));
        check.setStatus(statusDAOImp.read(resultSet.getInt(6)));
        return check;
    }
}
