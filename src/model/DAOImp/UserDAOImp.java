package model.DAOImp;

import model.DAO.RoleDAO;
import model.DAO.UserDAO;
import model.DBUtil.DSHolder;
import model.entities.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAOImp implements UserDAO {
    private static final Logger logger=Logger.getLogger(UserDAOImp.class);

    @Override
    public User create(User user) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="INSERT INTO Users(username,password,firstname,lastname,role) VALUES (?, ?, ?, ?, ?)";
        try{
            connection = DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setInt(5,user.getRole().getId());
            ps.executeUpdate();

            resultSet=ps.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                logger.info("Created User with id: " + id);
                user.setId(id);
            }
        }catch (SQLException e){
            logger.error("Can't add user "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
        }
        return user;
    }

    @Override
    public User readById(int id) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        User user=null;
        String sql="SELECT *FROM Users WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                user=executeUser(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read User "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return user;
    }

    @Override
    public User readByName(String name) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        User user=null;
        String sql="SELECT *FROM Users WHERE username=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setString(1, name);
            resultSet=ps.executeQuery();
            if(resultSet.next()){
                user=executeUser(resultSet);
            }
        }catch (SQLException e){
            logger.error("Can't read User "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return user;
    }

    @Override
    public Set<User> readAll() {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="SELECT * FROM Users";
        Set<User> users=null;
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            resultSet=ps.executeQuery();
            users = new HashSet<>();
            while(resultSet.next()){
                users.add(executeUser(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Users");
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return users;
    }

    @Override
    public void update(User user) {
        PreparedStatement ps=null;
        Connection connection=null;

        String sql="UPDATE Users Set username=?, password=?, firstname=?, lastname=?, role=? WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setInt(5, user.getRole().getId());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update the user "+ e);
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

        String sql="DELETE FROM Users WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete User "+ e);
            DSHolder.rollback(connection);
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    public User executeUser(ResultSet resultSet)throws SQLException{
        RoleDAO roleDAOImp=new RoleDAOImp();
        User user=new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setFirstName(resultSet.getString(4));
        user.setLastName(resultSet.getString(5));
        user.setRole(roleDAOImp.read(resultSet.getInt(6)));

        return user;
    }
}
