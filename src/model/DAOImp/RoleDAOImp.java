package model.DAOImp;

import model.DAO.RoleDAO;
import model.DBUtil.DSHolder;
import model.entities.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by miroslav on 02.03.16.
 */
public class RoleDAOImp implements RoleDAO {
    private static final Logger logger=Logger.getLogger(RoleDAOImp.class);
    Connection connection=null;
    Statement statement=null;
    PreparedStatement ps=null;
    ResultSet resultSet=null;

    @Override
    public Role create(Role role) {
        String sql="INSERT INTO Roles VALUES(?,?)";
        try{
            ps= DSHolder.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,readAll().size());
            ps.setString(2, role.getName());
            ps.executeUpdate();
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                role.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            logger.error("Can't add new Role "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return role;
    }

    @Override
    public Role read(int id) {
        String sql="SELECT *FROM Roles WHERE id=?";
        Role role=null;
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            role=new Role();
            if(resultSet.next()){
                role.setId(resultSet.getInt(1));
                role.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            logger.error("Can't read Role "+e);
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Set<Role> readAll() {
        Set<Role> roles=null;
        String sql="SELECT * FROM Roles";
        try{
            statement = DSHolder.getInstance().getConnection().createStatement();
            resultSet=statement.executeQuery(sql);
            roles=new HashSet<>();
            while(resultSet.next()){
                roles.add(executeRole(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Roles "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection, statement, resultSet);
        }
        return roles;
    }

    @Override
    public void update(Role role) {
        String sql="UPDATE Roles SET name=? WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Role "+e);
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
            String sql="DELETE FROM Roles WHERE id=?";
            try{
                ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
                ps.setInt(1,id);
                ps.executeUpdate();
            }catch (SQLException e){
                logger.error("Can't delete Role " + e);
                DSHolder.rollback(connection);
                e.printStackTrace();
            }finally {
                DSHolder.close(connection);
                DSHolder.close(resultSet);
                DSHolder.close(ps);
            }
    }

    public Role executeRole(ResultSet resultSet) throws SQLException{
        Role role=new Role();
        role.setId(resultSet.getInt(1));
        role.setName(resultSet.getString(2));
        return role;
    }
}
