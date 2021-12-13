package mni.code.service;

import mni.code.model.Comment;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface IComment {
    String createNewComment(Comment newComment) throws SQLException;
    List<Comment> fetchAllComment() throws SQLException;
    Comment fetchCommentByID(BigInteger id) throws SQLException;
    String updateCommentByID(BigInteger id, Comment dataComment) throws SQLException;
}
