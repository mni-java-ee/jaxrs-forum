package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Thread;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class ThreadService implements IThread {

    private final List<Thread> threads = new LinkedList<>();

    @Inject private Thread thread;
    @Inject private DbHelper dbHelper;

    @PostConstruct
    public void init() {
        thread = new Thread();
//        dbHelper = new DbHelper();
    }

    @Override
    public String createNewThread(Thread newThread) throws SQLException {
//        String query = "INSERT INTO TBL_THREAD (THREADNAME, THREADDATE, THREADCONTENT)"
//                + " VALUES ( '"+newThread.getThreadName() + "','" + newThread.getThreadDate() +"', '" + newThread.getThreadContent() +"')";
        String query = "{ ? = call package_thread.insert_new_thread('"+newThread.getThreadName() + "','" + newThread.getThreadDate() +"', '" + newThread.getThreadContent() +"')";
        String status = dbHelper.insertData(query);
      if (status != null) {
    		return "Berhasil ";
    	}else{
          return "Gagal";
    	}
    }

    @Override
    public String updateCurrentThread(BigInteger id, Thread currThread) throws SQLException {
        String query = "UPDATE TBL_THREAD " +
                "Set THREADNAME = '" +
                currThread.getThreadName() +
                "', THREADDATE = '" +
                currThread.getThreadDate() +
                "', THREADCONTENT = '" +
                currThread.getThreadContent() +
                "' WHERE ID = "+
                id;

        String sql = "{ ? = call package_thread.update_current_thread("+id+",'"+currThread.getThreadName()+"','"+currThread.getThreadDate()+"','"+currThread.getThreadContent()+"')";
        String result = dbHelper.updateDatabyId(sql);

		if(result != null){
			return "Berhasil";
		}else{
			return "Gagal";
		}
    }

    @Override
    public List<Thread> fetchAllThread() throws SQLException {
        if(!threads.isEmpty()){
            threads.clear();
        }
//        String sql = "SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM TBL_THREAD";
        String sql = "{call package_thread.get_all_thread(?)}";
        CallableStatement cs = dbHelper.getAllData(sql);
        ResultSet rs = (ResultSet)cs.getObject(1);
        while (rs.next()) {
            BigInteger id = BigInteger.valueOf(rs.getInt("ID_THREAD"));
            String threadName = rs.getString("THREAD_NAME");
            String threadDate = rs.getString("THREAD_DATE");
            String threadContent = rs.getString("THREAD_CONTENT");
            threads.add(new Thread(id, threadName, threadDate, threadContent));
        }
        return threads;
    }

    @Override
    public Thread fetchThreadById(BigInteger id) throws SQLException {
        deinitialize();
//        String sql = "SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM TBL_THREAD WHERE ID = " + id;
        String sql = "{call package_thread.get_single_thread("+id+", ?)}";
        CallableStatement cs = dbHelper.getSingleData(sql);
        ResultSet rs = (ResultSet)cs.getObject(1);

        if(rs != null){
            if (rs.next()) {
                thread.setId( BigInteger.valueOf(rs.getInt("ID_THREAD")));
                thread.setThreadName(rs.getString("THREAD_NAME"));
                thread.setThreadDate(rs.getString("THREAD_DATE"));
                thread.setThreadContent(rs.getString("THREAD_CONTENT"));
            }
            return thread;
        }
        
        return null;
    }

    @Override
    public String deleteThreadById(BigInteger id) throws SQLException {
        String sql = "DELETE FROM TBL_THREAD WHERE ID ="+ id;
        Boolean result = dbHelper.deleteById(sql);
        if(!result){
			return "Berhasil";
		}else{
			return "Gagal";
		}
    }

    private void deinitialize() {
        thread.setId(null);
        thread.setThreadName(null);
        thread.setThreadDate(null);
        thread.setThreadContent(null);
    }
}
