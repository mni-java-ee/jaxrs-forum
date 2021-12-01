package mni.code.service;

import mni.code.model.Thread;

import java.math.BigInteger;
import java.util.LinkedList;

public interface IThread {
    Thread createNewThread(Thread newThread);
    LinkedList<Thread> updateCurrentThread(BigInteger id, Thread currThread);
    LinkedList<Thread> fetchAllThread();
    Thread fetchThreadById(BigInteger id);
    LinkedList<Thread> deleteThreadById(BigInteger id);
}
