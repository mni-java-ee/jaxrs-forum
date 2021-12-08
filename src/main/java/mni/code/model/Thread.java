package mni.code.model;

import java.io.Serializable;
import java.math.BigInteger;

public class Thread implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

    private String threadName;

    private String threadDate;

    private String threadContent;
    
    public Thread(BigInteger id, String threadName, String threadDate, String threadContent) {
    	this.id = id;
    	this.threadName = threadName;
    	this.threadDate = threadDate;
    	this.threadContent = threadContent;
    }
    
    public Thread() {}

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadDate() {
        return threadDate;
    }

    public void setThreadDate(String threadDate) {
        this.threadDate = threadDate;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }
}
