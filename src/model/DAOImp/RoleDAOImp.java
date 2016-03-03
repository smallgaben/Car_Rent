package model.DAOImp;

import model.DAO.RoleDAO;
import model.DBUtil.DSHolder;
import model.entities.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDAOImp implements RoleDAO {
    private static final Logger logger=Logger.getLogger(RoleDAOImp.class);

    @Override
    public Role create(Role role) {
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="INSERT INTO Roles VALUES(?,?)";
        try{
            connection = DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,readAll().size());
            ps.setString(2, role.getName());
            ps.executeUpdate();
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                role.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            logger.error("Can't add new Role "+ e);
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
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet resultSet=null;

        String sql="SELECT *FROM Roles WHERE id=?";
        Role role=null;
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
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
        }finally{
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return role;
    }

    @Override
    public Set<Role> readAll() {
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Connection connection=null;

        Set<Role> roles=null;
        String sql="SELECT * FROM Roles";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            resultSet=ps.executeQuery();
            roles=new HashSet<>();
            while(resultSet.next()){
                roles.add(executeRole(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Roles "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return roles;
    }

    @Override
    public void update(Role role) {
        PreparedStatement ps=null;
        Connection connection=null;

        String sql="UPDATE Roles SET name=? WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Role "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;
        Connection connection = null;

        String sql = "DELETE FROM Roles WHERE id=?";
        try {
            connection = DSHolder.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete Role " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        } finally {
            DSHolder.close(connection);
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
