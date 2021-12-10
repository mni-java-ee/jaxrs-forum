package mni.code.service;

import mni.code.model.Comment;

import java.math.BigInteger;
import java.util.LinkedList;

public interface IComment {

    //Insert
    Comment createNewComment(Comment comment);
    //Fetch by ID
    Comment fetchCommentByID(BigInteger id);
    //Fetch All
    LinkedList<Comment> fetchAllComment();
    //Update
    LinkedList<Comment> updateCurrentComment(BigInteger id , Comment currComment);
    //Delete
    LinkedList<Comment> deleteComment(BigInteger id);

}
