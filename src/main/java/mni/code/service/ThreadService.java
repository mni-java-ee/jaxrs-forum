package mni.code.service;


import mni.code.connection.DbHelper;
import mni.code.model.Thread;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ThreadService implements IThread {

    @Inject
    private DbHelper dbHelper;

    @PostConstruct
    public void init() {
        List<Thread> threads = new ArrayList<>();
        Thread thread1 = new Thread();
        thread1.setId(new BigInteger("1"));
        thread1.setThreadName("Thread#1");
        thread1.setThreadDate("2021-11-30");
        thread1.setThreadContent("Ini thread ke-1");
        threads.add(thread1);
    }

    @Override
    public int createNewThread(Thread newThread) {
        String sql = "INSERT INTO tbl_thread (id_thread, thread_name, thread_date , thread_content) VALUES(" +
                "'" + newThread.getId() + "', " +
                "'" + newThread.getThreadName() + "', " +
                "'" + newThread.getThreadDate() + "', " +
                "'" + newThread.getThreadContent() + "')";
        return dbHelper.insertQueryGetID(sql);
    }

    @Override
    public boolean updateCurrentThread(BigInteger id, Thread currThread) {
        String sql = "UPDATE tbl_thread SET " +
                " thread_name = '" + currThread.getThreadName() + "'," +
                " thread_content = '" + currThread.getThreadContent() + "'," +
                " thread_date = '" + currThread.getThreadDate() + "' WHERE id_thread =" + id;
        return dbHelper.execQuery(sql);
    }

    @Override
    public List<Thread> fetchAllThread() {
        List<Thread> threadList = new ArrayList<>();
        ResultSet rs = dbHelper.SelectQuery("SELECT * FROM tbl_thread ORDER BY id_thread ASC");
        try {
            while (rs.next()){
                Thread t = new Thread();
                t.setId(BigInteger.valueOf(rs.getInt("id_thread")));
                t.setThreadName(rs.getString("thread_name"));
                t.setThreadContent(rs.getString("thread_content"));
                t.setThreadDate(rs.getString("thread_date"));

                threadList.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return threadList;
    }

    @Override
    public Thread fetchThreadById(BigInteger id) {
        Thread t = new Thread();
        String sql = "Select id_thread, thread_name, thread_content, thread_date From tbl_thread WHERE id_thread = " + id;
        ResultSet rs = dbHelper.SelectQuery(sql);
        try {
            if (rs.next()){
                t.setId(BigInteger.valueOf(rs.getInt("id_thread")));
                t.setThreadName(rs.getString("thread_name"));
                t.setThreadContent(rs.getString("thread_content"));
                t.setThreadDate(rs.getString("thread_date"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean deleteThreadById(BigInteger id) {
        String sql = "DELETE tbl_thread WHERE id_thread =" + id;
        return dbHelper.execQuery(sql);
    }
}
