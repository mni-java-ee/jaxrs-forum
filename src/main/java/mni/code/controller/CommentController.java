package mni.code.controller;

import mni.code.model.Comment;
import mni.code.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

@Path("/comment")
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Inject
    private CommentService commentService;

    @POST
    @Path("/createComment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewComment(Comment newComment) throws SQLException {
        String result = commentService.createNewComment(newComment);
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/getAllComment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchAllComment() throws SQLException {
        List<Comment> result = commentService.fetchAllComment();
        if(!result.isEmpty()){
            return Response.status(200).entity(result).build();
        }else{
            return Response.status(500).entity("Record Kosong").build();
        }
    }

    @GET
    @Path("/getCommentByID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchCommentByID(@PathParam("id")BigInteger id) throws SQLException {
        Comment comment = commentService.fetchCommentByID(id);
        if(comment.getIdComment() != null){
            return Response.status(200).entity(comment).build();
        }else{
            return Response.status(400).entity("Record Comment tidak ditemukan").build();
        }
    }

    @PUT
    @Path("/updateCommentByID/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCommentByID(@PathParam("id")BigInteger id, Comment dataComment) throws SQLException {
        String result = commentService.updateCommentByID(id, dataComment);
        return Response.status(200).entity(result).build();
    }
}
