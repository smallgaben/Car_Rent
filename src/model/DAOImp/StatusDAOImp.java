package model.DAOImp;

import model.DAO.StatusDAO;
import model.DBUtil.DSHolder;
import model.entities.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class StatusDAOImp implements StatusDAO {
    private static final Logger logger=Logger.getLogger(StatusDAOImp.class);

    @Override
    public Status create(Status status) {
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Connection connection=null;

        String sql="INSERT INTO Statuses VALUES(?,?)";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,readAll().size());
            ps.setString(2,status.getName());
            ps.executeUpdate();
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                status.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            logger.error("Can't add new Status "+e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
            DSHolder.close(ps);
        }
        return status;
    }

    @Override
    public Status read(int id) {
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Connection connection=null;

        String sql="SELECT *FROM Statuses WHERE id=?";
        Status status=null;
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            resultSet=ps.executeQuery();
            status = new Status();
            if(resultSet.next()){
                status.setId(resultSet.getInt(1));
                status.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            logger.error("Can't read Status "+e);
            e.printStackTrace();
        }finally{
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return status;
    }

    @Override
    public Set<Status> readAll() {
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        Connection connection=null;

        Set<Status> statuses=null;
        String sql="SELECT * FROM Statuses";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            resultSet=ps.executeQuery();
            statuses=new HashSet<>();
            while(resultSet.next()){
                statuses.add(executeStatus(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Statuses "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
            DSHolder.close(resultSet);
        }
        return statuses;
    }

    @Override
    public void update(Status status) {
        PreparedStatement ps=null;
        Connection connection=null;

        String sql="UPDATE Statuses SET name=? WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setString(1,status.getName());
            ps.setInt(2,status.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Status "+e);
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
        ResultSet resultSet=null;

        String sql="DELETE FROM Statuses WHERE id=?";
        try{
            connection=DSHolder.getInstance().getConnection();
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Status " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(ps);
        }

    }

    public Status executeStatus(ResultSet resultSet) throws SQLException{
        Status status=new Status();
        status.setId(resultSet.getInt(1));
        status.setName(resultSet.getString(2));
        return status;
    }

}
