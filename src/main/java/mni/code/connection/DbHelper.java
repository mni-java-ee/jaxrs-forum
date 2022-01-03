package mni.code.connection;

import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.sql.*;

@ApplicationScoped
public class DbHelper {
    private static Connection connection;

    @PostConstruct
    public  void connect(){
        if (connection == null){
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                DriverManager.registerDriver(new OracleDriver());
                connection = DriverManager.getConnection(url, "syafiq" , "Asdqwe123");
            }catch (SQLException t){
                System.out.println("There is no Connection!");
            }
        }
    }

    public int execFunction(String query){
        int num = 0;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("begin dbms_output.enable(); end;");
            num = statement.executeUpdate(query);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            num = -1;
        }
        return num;
    }

    public int insertFunction(String query){
        int num = 1;

        try {
            CallableStatement cs = connection.prepareCall(query);
            cs.registerOutParameter(1,OracleTypes.VARCHAR);
            cs.execute();
        }catch (SQLException e){
            e.printStackTrace();
            num = 0;
        }
        return num;
    }

    public int updateFunction(String query, BigInteger id){
        int num = 1;
        int temp = id.intValue();

        try {
            CallableStatement cs = connection.prepareCall(query);
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.setInt(1 , temp);
            cs.execute();
        }catch (SQLException e){
            e.printStackTrace();
            num = -1 ;
        }
        return num;
    }


    public int deleteFunction(String query, BigInteger id){
        int num = 1;
        int temp = id.intValue();

        try {
            CallableStatement cs = connection.prepareCall(query);
            cs.registerOutParameter(1,OracleTypes.INTEGER);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return num;
    }


    public ResultSet SelectQuery(String query){
        ResultSet rs = null;
        try {
            CallableStatement cs = connection.prepareCall(query);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
        }
        catch (SQLException t)
        {
            System.err.format("SQL State: %s\n%s", t.getSQLState(), t.getMessage());
            t.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
}

    public ResultSet SelectIdProcedure(String query, BigInteger id){
        ResultSet rs = null;
        int temp = id.intValue();
        try {
            CallableStatement cs = connection.prepareCall(query);
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.setInt(1 , temp);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
        }
        catch (SQLException t)
        {
            System.err.format("SQL State: %s\n%s", t.getSQLState(), t.getMessage());
            t.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean execQuery(String query){

        boolean result = false;

        try {
            Statement statement = connection.createStatement();
            result = statement.execute(query);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
