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
    Connection connection=null;
    Statement statement=null;
    PreparedStatement ps=null;
    ResultSet resultSet=null;

    @Override
    public Status create(Status status) {
        String sql="INSERT INTO Statuses VALUES(?,?)";
        try{
            ps= DSHolder.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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
        String sql="SELECT FROM Statuses WHERE id=?";
        Status status=null;
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
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
        }
        return status;
    }

    @Override
    public Set<Status> readAll() {
        Set<Status> statuses=null;
        String sql="SELECT * FROM Statuses";
        try{
            statement = DSHolder.getInstance().getConnection().createStatement();
            resultSet=statement.executeQuery(sql);
            statuses=new HashSet<>();
            while(resultSet.next()){
                statuses.add(executeStatus(resultSet));
            }
        }catch (SQLException e){
            logger.error("Can't read all Statuses "+e);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection, statement, resultSet);
        }
        return statuses;
    }

    @Override
    public void update(Status status) {
        String sql="UPDATE Statuses SET name=? WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1,status.getName());
            ps.setInt(2,status.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't update Status "+e);
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
        String sql="DELETE FROM Statuses WHERE id=?";
        try{
            ps=DSHolder.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            logger.error("Can't delete Status " + e);
            DSHolder.rollback(connection);
            e.printStackTrace();
        }finally {
            DSHolder.close(connection);
            DSHolder.close(resultSet);
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
