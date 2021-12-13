package mni.code.model;

import java.math.BigInteger;

public class Comment {
    private BigInteger commentId;
    private BigInteger threadId;
    private String comments;
    private String commentDate;


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

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }


    public BigInteger getThreadId() {
        return threadId;
    }

    public void setThreadId(BigInteger threadId) {
        this.threadId = threadId;
    }
}
