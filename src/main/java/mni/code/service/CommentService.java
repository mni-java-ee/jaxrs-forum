package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Comment;
import mni.code.model.Thread;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class CommentService implements IComment{

    private final List<Comment> comments= new LinkedList<>();

    private Comment comment;

    @Inject
    private DbHelper dbHelper;

    @Override
    public String createNewComment(Comment newComment) throws SQLException {
//        String sql = "INSERT INTO TBL_COMMENT (CONTENT_COMMENT, DATE_COMMENT) VALUES ('"+ newComment.getContent() +"', TO_DATE('"+ newComment.getDateComment() +"','YYYY/MM/DD'))";
        String sql = "{ ? = call comment_cursor.insert_New_Comment('"+newComment.getContent()+"', '"+newComment.getDateComment()+"') }";
        String result = dbHelper.insertData(sql);
        if(!result.isEmpty()){
            return "Menambahkan Comment telah berhasil";
        }else{
            return "Menambahkan Comment gagal";
        }
    }

    @Override
    public List<Comment> fetchAllComment() throws SQLException {
        if(!comments.isEmpty()){
            comments.clear();
        }

        String sql = "{call comment_cursor.get_all_comments(?)}";
//        ResultSet rs = dbHelper.getAllData(sql);
        CallableStatement cs = dbHelper.getAllData(sql);
        ResultSet rs = (ResultSet)cs.getObject(1);

        while(rs.next()){
            BigInteger id = BigInteger.valueOf(rs.getInt("ID_COMMENT"));
            String contentComment = rs.getString("CONTENT_COMMENT");
            String dateComment = rs.getString("DATE_COMMENT");
            comments.add(new Comment(id, contentComment, dateComment));
        }
        return comments;
    }

    @Override
    public Comment fetchCommentByID(BigInteger id) throws SQLException {
        comment = new Comment();
//      String sql = "SELECT * FROM TBL_COMMENT WHERE ID_COMMENT = "+id;
        String sql = "{call comment_cursor.get_comment_by_id("+ id +", ?)}";
        CallableStatement cs = dbHelper.getSingleData(sql);
        ResultSet rs = (ResultSet)cs.getObject(1);
        if(rs != null){
            while(rs.next()) {
                id = BigInteger.valueOf(rs.getInt("ID_COMMENT"));
                String contentComment = rs.getString("CONTENT_COMMENT");
                String dateComment = rs.getString("DATE_COMMENT");
                comment.setIdComment(id);
                comment.setDateComment(contentComment);
                comment.setDateComment(dateComment);
                return comment;
            }
        }
        return null;
    }

    @Override
    public String updateCommentByID(BigInteger id, Comment dataComment) throws SQLException {
//        String sql = "UPDATE TBL_COMMENT " +
//                "SET CONTENT_COMMENT = '" + dataComment.getContent() +
//                "', DATE_COMMENT = TO_DATE('"+ dataComment.getDateComment() +"', 'YYYY/MM/DD') " +
//                "WHERE ID_COMMENT = "+ id;

        String sql = "{? = call comment_cursor.update_current_comment('"+id+"','"+dataComment.getContent()+"','"+dataComment.getDateComment()+"')}";
        String result = dbHelper.updateDatabyId(sql);
        if(!result.isEmpty()){
            return "Update Data Yang dilakukan Berhasil";
        }else{
            return "Update Data Yang dilakukan gagal";
        }
    }
}
