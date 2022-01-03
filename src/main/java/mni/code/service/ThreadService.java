package mni.code.service;


import mni.code.connection.DbHelper;
import mni.code.model.Thread;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class ThreadService implements IThread {

    @Inject
    private DbHelper dbHelper;

    @PostConstruct
    public void init() {
        List<Thread> threads = new ArrayList<>();
        Thread thread1 = new Thread();
//        thread1.setId(new BigInteger("1"));
//        thread1.setThreadName("Thread#1");
//        thread1.setThreadDate("2021-11-30");
//        thread1.setThreadContent("Ini thread ke-1");
//        threads.add(thread1);
    }

    @Override
    public int createNewThread(Thread newThread) {
        String sql = "{ ? = call thread_pkg.fn_add_thread('"+newThread.getThreadName()+"','"+newThread.getThreadContent()+"')}";
        return dbHelper.insertFunction(sql);
    }

    @Override
    public int updateCurrentThread(BigInteger id, Thread currThread) {
        String sql = "{ ? = call thread_pkg.fn_update_thread("+id+",'"+currThread.getThreadName()+"','"+currThread.getThreadContent()+"')}";
        return dbHelper.insertFunction(sql);
    }

    @Override
    public List<Thread> fetchAllThread() {
        List<Thread> threadList = new ArrayList<>();
        String sql = "{call thread_pkg.pr_fetch_all_thread(?)}";
        ResultSet rs = dbHelper.SelectQuery(sql);
        try {
            while (rs.next()){
                Thread t = new Thread();
                t.setId(BigInteger.valueOf(rs.getInt("id_thread")));
                t.setThreadName(rs.getString("thread_name"));
                t.setThreadContent(rs.getString("thread_content"));
                t.setThreadDate(rs.getDate("thread_date"));

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
        String sql = "{call thread_pkg.pr_fetch_id_thread(?,?)}";
        ResultSet rs = dbHelper.SelectIdProcedure(sql,id);
        try {
            if (rs.next()){
                t.setId(BigInteger.valueOf(rs.getInt("id_thread")));
                t.setThreadName(rs.getString("thread_name"));
                t.setThreadContent(rs.getString("thread_content"));
                t.setThreadDate(rs.getDate("thread_date"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public int deleteThreadById(BigInteger id) {
        String sql = "{? = call thread_pkg.fn_delete_thread("+id+")}";
        return dbHelper.deleteFunction(sql,id);
    }
}
