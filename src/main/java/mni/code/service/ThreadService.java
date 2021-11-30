package mni.code.service;

import jakarta.enterprise.context.ApplicationScoped;
import mni.code.model.Thread;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
public class ThreadService implements IThread{

    private final LinkedList<Thread> threads = new LinkedList<>();

    @PostConstruct
    public void init(){
        Thread thread1 = new Thread();
        thread1.setId(new BigInteger("1"));
        thread1.setThreadName("Thread#1");
        thread1.setThreadDate("2021-11-30");
        thread1.setThreadContent("Ini thread ke-1");
        threads.add(thread1);
    }

    @Override
    public Thread createNewThread(Thread newThread) {
        threads.add(newThread);
        return threads.getLast();
    }

    @Override
    public Thread updateCurrentThread(Thread currThread) {
        return null;
    }

    @Override
    public LinkedList<Thread> fetchAllThread() {
        return threads;
    }

    @Override
    public Thread fetchThreadById(BigInteger id) {
        Optional<Thread> optCurrentThread = threads.stream().filter(t -> t.getId().compareTo(id)==0).findAny();
        return optCurrentThread.orElse(null);
    }
}
