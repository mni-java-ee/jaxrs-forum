package mni.code.service;


import mni.code.connection.DbHelper;
import mni.code.model.Thread;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ThreadService implements IThread {

    private final List<Thread> threads = new LinkedList<>();
    private Thread thread = new Thread();
    private DbHelper dbHelper = new DbHelper();

    @PostConstruct
    public void init() {

    }

    @Override
    public Thread createNewThread(Thread newThread) throws SQLException {
        String query = "INSERT INTO TBL_THREAD (THREADNAME, THREADDATE, THREADCONTENT)"
                + " VALUES ( '"+newThread.getThreadName() + "','" + newThread.getThreadDate() +"', '" + newThread.getThreadContent() +"')";
        String result = dbHelper.insertData(query);
        return newThread;
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
        String result = dbHelper.updateDatabyId(query);
        return result;
    }

    @Override
    public List<Thread> fetchAllThread() throws SQLException {
        if(!threads.isEmpty()){
            threads.clear();
        }
        String sql = "SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM TBL_THREAD";
        ResultSet rs = dbHelper.getAllData(sql);
        while (rs.next()) {
            BigInteger id = BigInteger.valueOf(rs.getInt("ID"));
            String threadName = rs.getString("THREADNAME");
            String threadDate = rs.getString("THREADDATE");
            String threadContent = rs.getString("THREADCONTENT");
            threads.add(new Thread(id, threadName, threadDate, threadContent));
        }
        return threads;
    }

    @Override
    public Thread fetchThreadById(BigInteger id) throws SQLException {
        thread.setId(null);
        thread.setThreadName(null);
        thread.setThreadDate(null);
        thread.setThreadContent(null);
        String sql = "SELECT ID, THREADNAME, THREADDATE, THREADCONTENT FROM TBL_THREAD WHERE ID = " + id;
        ResultSet rs = dbHelper.getSingleData(sql);
        if(rs != null){
            while (rs.next()) {
                thread.setId( BigInteger.valueOf(rs.getInt("ID")));
                thread.setThreadName(rs.getString("THREADNAME"));
                thread.setThreadDate(rs.getString("THREADDATE"));
                thread.setThreadContent(rs.getString("THREADCONTENT"));
            }
            return thread;
        }else{
            return null;
        }
    }

    @Override
    public String deleteThreadById(BigInteger id) throws SQLException {
        String sql = "DELETE FROM TBL_THREAD WHERE ID ="+ id;
        String result = dbHelper.deleteById(sql);
        return result;
    }
}
