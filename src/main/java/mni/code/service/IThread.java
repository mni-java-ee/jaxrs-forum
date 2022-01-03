package mni.code.service;

import mni.code.model.Thread;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public interface IThread {
    int createNewThread(Thread newThread);
    int updateCurrentThread(BigInteger id, Thread currThread);
    List<Thread> fetchAllThread();
    Thread fetchThreadById(BigInteger id);
    int deleteThreadById(BigInteger id);
}
