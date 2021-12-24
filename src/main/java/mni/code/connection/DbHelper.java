package mni.code.connection;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

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
    
    public String insertData(String sql) throws SQLException {
//    	PreparedStatement preparedStmt = koneksi.prepareStatement(sql);
//		sql = "{ ? = call insertNewComment(?, ?)}";
		CallableStatement cstmt = koneksi.prepareCall(sql);
		cstmt.registerOutParameter(1, Types.VARCHAR);
//		cstmt.setString(2, "Testing harcode XX");
//		cstmt.setString(3, "2021/12/12");
		cstmt.execute();
    	return cstmt.getString(1);
    }
    
    public CallableStatement getAllData(String sql) throws SQLException {
//    	ResultSet rs;
    	CallableStatement cstmt = koneksi.prepareCall(sql);
		cstmt.registerOutParameter(1, OracleTypes.CURSOR);
    	cstmt.executeUpdate();
//		rs = (ResultSet)cstmt.getObject(1);
    	return cstmt;
    }
    
    public CallableStatement getSingleData(String sql) throws SQLException {
		CallableStatement cstmt = koneksi.prepareCall(sql);
//		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.registerOutParameter(1, OracleTypes.CURSOR);
		cstmt.executeUpdate();
    	return cstmt;
    }

	public String updateDatabyId(String sql) throws SQLException {
//		Statement stmt = koneksi.createStatement();
		CallableStatement cstmt = koneksi.prepareCall(sql);
		cstmt.registerOutParameter(1, Types.VARCHAR);
		cstmt.execute();
		return cstmt.getString(1);
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
