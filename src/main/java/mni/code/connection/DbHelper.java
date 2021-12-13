package mni.code.connection;

import oracle.jdbc.OracleDriver;

import java.sql.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbHelper {

	private static Connection koneksi;

	@PostConstruct
	private void init(){
    	try {
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				DriverManager.registerDriver(new OracleDriver());
				koneksi = DriverManager.getConnection(url, "tikto", "tikto1234");
    	} catch(Exception e) {
    		//System.out.println(e.getStackTrace()[0]);
    		e.printStackTrace();
    	}
    }
    
    public String tesKoneksi(){
    	String result = "";	
        if(koneksi == null) {
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                DriverManager.registerDriver(new OracleDriver());
                koneksi = DriverManager.getConnection(url, "tikto", "tikto1234");
                result = "Koneksi baru Berhasil";
            } catch (SQLException t) {
                result = "koneksi baru gagal !";
            }
        }else {
        	result = "Koneksi sudah ada dan tidak ada perubahan";
        }
        return result;
    }
    
    public Boolean insertData(String sql) throws SQLException {
    	PreparedStatement preparedStmt = koneksi.prepareStatement(sql);
    	return preparedStmt.execute();
    }
    
    public ResultSet getAllData(String sql) throws SQLException {
		Statement stmt = koneksi.createStatement();
    	ResultSet rs;
    	rs = stmt.executeQuery(sql);
    	return rs;
    }
    
    public ResultSet getSingleData(String sql) throws SQLException {
    	Statement stmt = koneksi.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery(sql);
    	return rs;
    }

	public Boolean updateDatabyId(String sql) throws SQLException {
		Statement stmt = koneksi.createStatement();
		return stmt.execute(sql);
	}

	public Boolean deleteById(String sql) throws SQLException{
		Statement stmt = koneksi.createStatement();
		return stmt.execute(sql);
	}

	public ResultSet executeSQL(String sql) throws SQLException{
		Statement stmt = koneksi.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery(sql);
		return rs;
	}
}
