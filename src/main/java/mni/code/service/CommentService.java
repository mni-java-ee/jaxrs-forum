package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Comment;

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
        String sql = "{ ? = call comment_pkg.fn_add_comment('"+comment.getComments()+"',"+comment.getThreadId()+")}";
        return dbHelper.insertFunction(sql);
    }

    @Override
    public Comment fetchCommentByID(BigInteger id) {
        Comment c = new Comment();
        String sql = "{call comment_pkg.fetch_id_comment(?,?)}";
        ResultSet rs = dbHelper.SelectIdProcedure(sql,id);
        try {
            if (rs.next()) {
                c.setCommentId(BigInteger.valueOf(rs.getInt("ID_COMMENT")));
                c.setThreadId(BigInteger.valueOf(rs.getInt("ID_THREAD")));
                c.setComments(rs.getString("COMMENTS"));
                c.setCommentDate(rs.getDate("COMMENT_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Comment> fetchAllComment() {
        List<Comment> commentList = new ArrayList<>();
        String sql = "{call comment_pkg.pc_fetch_all(?)}";
        ResultSet rs = dbHelper.SelectQuery(sql);
        try {
            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentId(BigInteger.valueOf(rs.getInt("ID_COMMENT")));
                c.setThreadId(BigInteger.valueOf(rs.getInt("ID_THREAD")));
                c.setComments(rs.getString("COMMENTS"));
                c.setCommentDate(rs.getDate("COMMENT_DATE"));
                commentList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;

    }

    @Override
    public int updateCurrentComment(BigInteger id, Comment currComment) {
        String sql = "{ ? = call comment_pkg.fn_update_comment("+id+",'"+currComment.getComments()+"')}";
        return dbHelper.insertFunction(sql);
    }


    @Override
    public int deleteComment(BigInteger id) {
        String sql = "DECLARE\n" +
                "    flag NUMBER;\n" +
                "    BEGIN\n" +
                "        flag := comment_pkg.fn_delete_comment("+id+");\n" +
                "    END;";
        return dbHelper.execFunction(sql);
    }
}
