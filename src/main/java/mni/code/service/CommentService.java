package mni.code.service;

import mni.code.connection.DbHelper;
import mni.code.model.Comment;
import mni.code.model.Thread;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO TBL_COMMENT (CONTENT_COMMENT, DATE_COMMENT) VALUES ('"+ newComment.getContent() +"', TO_DATE('"+ newComment.getDateComment() +"','YYYY/MM/DD'))";
        Boolean result = dbHelper.insertData(sql);
        if(!result){
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

        String sql = "SELECT * FROM TBL_COMMENT";
        ResultSet rs = dbHelper.getAllData(sql);

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
        String sql = "SELECT * FROM TBL_COMMENT WHERE ID_COMMENT = "+id;
        ResultSet rs = dbHelper.getSingleData(sql);
        if(rs != null){
            if(rs.next()){
                String contentComment = rs.getString("CONTENT_COMMENT");
                String dateComment = rs.getString("DATE_COMMENT");
                comment.setIdComment(id);
                comment.setContent(contentComment);
                comment.setDateComment(dateComment);
            }
            return comment;
        }
        return null;
    }

    @Override
    public String updateCommentByID(BigInteger id, Comment dataComment) throws SQLException {
        String sql = "UPDATE TBL_COMMENT " +
                "SET CONTENT_COMMENT = '" + dataComment.getContent() +
                "', DATE_COMMENT = TO_DATE('"+ dataComment.getDateComment() +"', 'YYYY/MM/DD') " +
                "WHERE ID_COMMENT = "+ id;
        Boolean result = dbHelper.updateDatabyId(sql);
        if(!result){
            return "Update Data Yang dilakukan Berhasil";
        }else{
            return "Update Data Yang dilakukan gagal";
        }
    }
}
