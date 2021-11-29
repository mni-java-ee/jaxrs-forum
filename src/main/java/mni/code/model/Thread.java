package mni.code.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "thread")
public class Thread implements Serializable {

    @XmlAttribute(name = "id")
    private BigInteger id;

    @XmlAttribute(name = "threadName")
    private String threadName;

    @XmlAttribute(name = "threadDate")
    private String threadDate;

    @XmlAttribute(name = "threadContent")
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
