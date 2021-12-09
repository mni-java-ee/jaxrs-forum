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
  	
    	String query = "INSERT INTO Threads (THREADNAME, THREADDATE, THREADCONTENT)"
    			+ " VALUES ( ?, ?, ?)";
    	
    	PreparedStatement preparedStmt = koneksi.prepareStatement(query);
    	preparedStmt.setString(1, newThread.getThreadName());
    	preparedStmt.setString(2, newThread.getThreadDate());
    	preparedStmt.setString(3, newThread.getThreadContent());
    	Boolean status = preparedStmt.execute();
    	if (!status) {
    		result = "Berhasil ";
    	}
    	return result;
    }
    
    public List<Thread> getAllData() throws SQLException {
    	Statement stmt = koneksi.createStatement();
    	ResultSet rs;

		if(!threadList.isEmpty()){
			threadList.clear();
		}
    	
    	rs = stmt.executeQuery("SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM THREADS");
 
    	while (rs.next()) {
    		BigInteger id = BigInteger.valueOf(rs.getInt("ID"));
    		String threadName = rs.getString("THREADNAME");
    		String threadDate = rs.getString("THREADDATE");
    		String threadContent = rs.getString("THREADCONTENT");
    		threadList.add(new Thread(id, threadName, threadDate, threadContent));
		}	
    	return threadList; 
    }
    
    public List<Thread> getSingleData(Integer idTarget) throws SQLException {
    	Statement stmt = koneksi.createStatement();
		ResultSet rs;

		if(!threadList.isEmpty()){
			threadList.clear();
		}

		rs = stmt.executeQuery("SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM THREADS WHERE id = "+ idTarget);

		while (rs.next()) {
			BigInteger id = BigInteger.valueOf(rs.getInt("ID"));
			String threadName = rs.getString("THREADNAME");
			String threadDate = rs.getString("THREADDATE");
			String threadContent = rs.getString("THREADCONTENT");
			threadList.add(new Thread(id, threadName, threadDate,threadContent));
		}
    	return threadList;
    }

	public String updateDatabyId(Integer idTarget, Thread updatedData) throws SQLException {
		Statement stmt = koneksi.createStatement();

		String threadName = updatedData.getThreadName();
		String threadDate = updatedData.getThreadDate();
		String threadContent = updatedData.getThreadContent();

		String sqlQuery = "UPDATE THREADS SET " +
				"THREADNAME = '"+ threadName +
				"', THREADDATE = '"+ threadDate +
				"', THREADCONTENT ='"+ threadContent +
				"' WHERE ID = "+ idTarget;

		Boolean result = stmt.execute(sqlQuery);

		if(!result){
			return "Berhasil";
		}else{
			return "Gagal";
		}
	}

	public String deleteById(Integer idTarget) throws SQLException{
		Statement stmt = koneksi.createStatement();
		Boolean result = stmt.execute("DELETE FROM THREADS WHERE ID = "+ idTarget);
		if(!result){
			return "Berhasil";
		}else{
			return "Gagal";
		}
	}
}
