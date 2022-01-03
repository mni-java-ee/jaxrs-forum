package mni.code.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Thread implements Serializable {

    private BigInteger id;

    private String threadName;

    private Date threadDate;

    private String threadContent;

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

    public Date getThreadDate() {
        return threadDate;
    }

    public void setThreadDate(Date threadDate) {
        this.threadDate = threadDate;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }
}
