package model.DAOImp;

import model.DAO.ClassDAO;
import model.DBUtil.DSHolder;
import model.entities.Class;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClassDAOImp implements ClassDAO {
    private static final Logger logger=Logger.getLogger(CarDAOImp.class);

    @Override
    public Class create(Class addClass) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="INSERT INTO Classes VALUES(?,?)";
        try{
            connection= DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,readAll().size());
            ps.setString(2,addClass.getName());
            ps.executeUpdate();
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                addClass.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            logger.error("Can't add new Class "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return addClass;
    }

    @Override
    public Class read(int id) {
        PreparedStatement ps=null;
        Connection connection = null;
        ResultSet resultSet=null;

        String sql="SELECT *FROM Classes WHERE id=?";
        Class carClass=null;
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            carClass=new Class();
            if(resultSet.next()){
                carClass.setId(resultSet.getInt(1));
                carClass.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            logger.error("Can't read Class "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return carClass;
    }

    @Override
    public Set<Class> readAll() {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        Set<Class> classes=null;
        String sql="SELECT * FROM Classes";
        try{
            connection= DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            resultSet=ps.executeQuery();
            classes=new HashSet<>();
            while(resultSet.next()){
                classes.add(executeClass(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Classes "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return classes;
    }

    @Override
    public void update(Class carClass) {
        PreparedStatement ps=null;
        Connection connection=null;

        String sql="UPDATE Classes SET name=? WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setString(1, carClass.getName());
            ps.setInt(2, carClass.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Classes "+e);
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

        String sql="DELETE FROM Classes WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Class " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    public Class executeClass(ResultSet resultSet) throws SQLException{
        Class carClass=new Class();
        carClass.setId(resultSet.getInt(1));
        carClass.setName(resultSet.getString(2));
        return carClass;
    }
}
