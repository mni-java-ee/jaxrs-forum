package mni.code.connection;

import oracle.jdbc.OracleDriver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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

    public  int insertQueryGetID(String query){
        int num = 0;

        try {
            Statement statement = connection.createStatement();
            num = statement.executeUpdate(query);
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            num = -1;
        }
        return num;
    }

    public ResultSet SelectQuery(String query){
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
        }catch (SQLException e){
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
