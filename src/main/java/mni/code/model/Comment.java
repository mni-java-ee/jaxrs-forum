package mni.code.model;

import java.math.BigInteger;
import java.util.Date;

public class Comment {
    private BigInteger commentId;
    private BigInteger threadId;
    private String comments;
    private Date commentDate;



    public BigInteger getCommentId() {
        return commentId;
    }

    public void setCommentId(BigInteger commentId) {
        this.commentId = commentId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }


    public BigInteger getThreadId() {
        return threadId;
    }

    public void setThreadId(BigInteger threadId) {
        this.threadId = threadId;
    }
}
