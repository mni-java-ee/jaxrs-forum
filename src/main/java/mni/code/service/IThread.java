package mni.code.service;

import mni.code.model.Thread;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface IThread {
    Thread createNewThread(Thread newThread) throws SQLException;
    String updateCurrentThread(BigInteger id, Thread currThread) throws SQLException;
    List<Thread> fetchAllThread() throws SQLException;
    Thread fetchThreadById(BigInteger id) throws SQLException;
    String deleteThreadById(BigInteger id) throws SQLException;
}
