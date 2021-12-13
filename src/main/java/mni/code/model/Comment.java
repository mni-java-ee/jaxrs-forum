package mni.code.model;

import java.math.BigInteger;

public class Comment {
    private BigInteger idComment;
    private String content;
    private String dateComment;

    public Comment(BigInteger idComment, String content, String dateComment){
        this.idComment = idComment;
        this.content = content;
        this.dateComment = dateComment;
    }

    public Comment(){}

    public BigInteger getIdComment() {
        return idComment;
    }

    public void setIdComment(BigInteger idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }
}
