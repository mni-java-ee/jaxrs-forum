package mni.code.connection;

import oracle.jdbc.OracleDriver;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import mni.code.model.Thread;

@ApplicationScoped
public class DbHelper {
	
	private List<Thread> threadList = new ArrayList<Thread>();
	
    private static Connection koneksi;
    
    @PostConstruct
    private void init() {
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
    
    public String insertData(Thread newThread) throws SQLException {
    	String result = "Gagal";
  	
    	String query = "INSERT INTO Threads (ID, THREADNAME, THREADDATE, THREADCONTENT)"
    			+ " VALUES ( ?, ?, ?, ?)";
    	
    	PreparedStatement preparedStmt = koneksi.prepareStatement(query);
    	preparedStmt.setInt(1, newThread.getId().intValue());
    	preparedStmt.setString(2, newThread.getThreadName());
    	preparedStmt.setString(3, newThread.getThreadDate());
    	preparedStmt.setString(4, newThread.getThreadContent());
    	Boolean status = preparedStmt.execute();
    	if (status) {
    		result = "Berhasil "+status;
    	}
    	return result;
    }
    
    public List<Thread> getAllData() throws SQLException {
    	Statement stmt = koneksi.createStatement();
    	ResultSet rs;
    	
    	rs = stmt.executeQuery("SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM THREADS");
 
    	while (rs.next()) {
    		BigInteger id = BigInteger.valueOf(rs.getInt("ID"));
    		String threadName = rs.getString("THREADNAME");
    		String threadDate = rs.getString("THREADDATE");
    		String threadContent = rs.getString("THREADCONTENT");
    		System.out.println("threadID" + id);
    		System.out.println("threadName :" + threadName);
    		System.out.println("threadDate :" + threadDate);
    		System.out.println("threadContent :" + threadContent);
    		threadList.add(new Thread(id, threadName, threadDate, threadContent));
		}	
    	return threadList; 
    }
    
    public Thread getSingleData(Integer id) throws SQLException {
    	Statement stmt = koneksi.createStatement();
    	return null;
    }
    
    public List<Thread> getThreadList(){
		return threadList;
    }
}
