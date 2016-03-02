package model.DBUtil;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DSHolder {
    private static final Logger logger=Logger.getLogger(DSHolder.class);
    private static DSHolder instance;
    private DataSource ds;

    public static synchronized DSHolder getInstance(){
        if(instance==null){
            instance = new DSHolder();
        }
        return instance;
    }

    private DSHolder(){
        logger.info("Making Data Source");
        try{
            Context initContext=new InitialContext();
            Context envContext=(Context) initContext.lookup("java:/comp/env");

            ds=(DataSource) envContext.lookup("jdbc/CarRent");
            logger.info("The Data Source: "+ ds);

        }catch (NamingException e){
            logger.error("Can't name the Data Source: "+ e);
            e.printStackTrace();
        }

    }

    /**
     * This method returns the database connection
     * from Data Source
     * @return DB Connection
     */
    public Connection getConnection(){
        Connection connection=null;

        try{
            connection = ds.getConnection();
        }catch(SQLException e){
            logger.error("Connection failed "+ e);
            e.printStackTrace();
        }
        return connection;
    }

}
