package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Comment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.LinkedList;


@ApplicationScoped
public class CommentService implements IComment{

@Inject
private DbHelper dbHelper;

    @Override
    public Comment createNewComment(Comment comment) {
        try {
            String sql = "INSERT INTO COMMENT_TABLE(id, comments, comment_date, id_thread) VALUES(" +
                    "'" + comment.getId() + "', " +
                    "'" + comment.getComments() + "', " +
                    "'" + comment.getCommentDate() + "', " +
                    "'" + comment.getThread().getId() + "')";
            dbHelper.insertQueryGetID(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public Comment fetchCommentByID(BigInteger id) {
        return null;
    }

    @Override
    public LinkedList<Comment> fetchAllComment() {
        return null;
    }

    @Override
    public LinkedList<Comment> updateCurrentComment(BigInteger id, Comment currComment) {
        return null;
    }

    @Override
    public LinkedList<Comment> deleteComment(BigInteger id) {
        return null;
    }
}
