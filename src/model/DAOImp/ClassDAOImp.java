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
    Connection connection=null;
    Statement statement=null;
    PreparedStatement ps=null;
    ResultSet resultSet=null;

    @Override
    public Class create(Class addClass) {
        String sql="INSERT INTO Classes VALUES(?,?)";
        try{
            ps= DSHolder.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,readAll().size());
            ps.setString(2,addClass.getName());
            ps.executeUpdate();
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                addClass.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            logger.error("Can't add new Car Class "+e);
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
        String sql="SELECT *FROM Classes WHERE id=?";
        Class carClass=null;
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            carClass=new Class();
            if(resultSet.next()){
                carClass.setId(resultSet.getInt(1));
                carClass.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            logger.error("Can't read Car Class "+e);
            e.printStackTrace();
        }
        return carClass;
    }

    @Override
    public Set<Class> readAll() {
        Set<Class> classes=null;
        String sql="SELECT * FROM Classes";
        try{
            statement = DSHolder.getInstance().getConnection().createStatement();
            resultSet=statement.executeQuery(sql);
            classes=new HashSet<>();
            while(resultSet.next()){
                classes.add(executeClass(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Classes "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection, statement, resultSet);
        }
        return classes;
    }

    @Override
    public void update(Class carClass) {
        String sql="UPDATE Classes SET name=? WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, carClass.getName());
            ps.setInt(2, carClass.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Classes "+e);
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
        String sql="DELETE FROM Classes WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Class " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
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
