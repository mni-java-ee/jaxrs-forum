package mni.code.connection;

import oracle.jdbc.OracleDriver;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;

@ApplicationScoped
public class DbHelper {
    private static Connection connection;

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

    public  int insertQueryGetID(String query){
        connect();
        int num = 0;
        int rslt = -1;
        try {
            Statement statement = connection.createStatement();
            num = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                rslt = rs.getInt(1);
            }
            rs.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
            rslt = -1;
        }
        return rslt;
    }

    public boolean execQuery(String query){
        connect();
        boolean result = false;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            result = true;

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
