package mni.code.service;

import mni.code.model.Comment;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface IComment {

    //Insert
    int createNewComment(Comment comment);
    //Fetch by ID
    Comment fetchCommentByID(BigInteger id);
    //Fetch All
    List<Comment> fetchAllComment();
    //Update
    int updateCurrentComment(BigInteger id , Comment currComment);
    //Delete
    int deleteComment(BigInteger id);

}
