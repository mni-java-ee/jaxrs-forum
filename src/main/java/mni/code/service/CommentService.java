package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Comment;
import mni.code.model.Thread;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class CommentService implements IComment {

    @Inject
    private DbHelper dbHelper;

    @Override
    public int createNewComment(Comment comment) {
        String sql = "INSERT INTO tbl_comment (id_comment, comments, comment_date, id_thread) VALUES(" +
                "'" + comment.getCommentId() + "', " +
                "'" + comment.getComments() + "', " +
                "'" + comment.getCommentDate() + "', " +
                "'" + comment.getThreadId() + "')";
        return dbHelper.insertQueryGetID(sql);
    }

    @Override
    public Comment fetchCommentByID(BigInteger id) {
        Comment c = new Comment();
        String sql = "Select id_comment, id_thread , comments, comment_date From tbl_comment WHERE id_comment = " + id;
        ResultSet rs = dbHelper.SelectQuery(sql);
        try {
            if (rs.next()) {
                Thread t = new Thread();
                c.setCommentId(BigInteger.valueOf(rs.getInt(1)));
                c.setCommentId(BigInteger.valueOf(rs.getInt(2)));
                c.setComments(rs.getString(3));
                c.setCommentDate(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Comment> fetchAllComment() {
        List<Comment> commentList = new ArrayList<>();
        ResultSet rs = dbHelper.SelectQuery("SELECT * FROM tbl_comment ORDER BY ID_COMMENT ASC");
        try {
            while (rs.next()) {
                Comment c = new Comment();
                Thread t = new Thread();
                c.setCommentId(BigInteger.valueOf(rs.getInt("ID_COMMENT")));
                c.setThreadId(BigInteger.valueOf(rs.getInt("ID_THREAD")));
                c.setComments(rs.getString("COMMENTS"));
                c.setCommentDate(rs.getString("COMMENT_DATE"));

                commentList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;

    }

    @Override
    public boolean updateCurrentComment(BigInteger id, Comment currComment) {
        String sql = "UPDATE tbl_comment SET " +
                " comments = '" + currComment.getComments() + "'," +
                " comment_date = '" + currComment.getCommentDate() + "' WHERE id_comment =" + id;
        return dbHelper.execQuery(sql);
    }


    @Override
    public boolean deleteComment(BigInteger id) {
        String sql = "DELETE tbl_comment WHERE ID_COMMENT =" + id;
        return dbHelper.execQuery(sql);
    }
}
