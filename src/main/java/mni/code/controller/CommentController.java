package mni.code.controller;

import mni.code.model.Comment;
import mni.code.service.CommentService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Path("/comment-mgmt")
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Inject
    private CommentService commentService;

    @POST
    @Path("/createNewComment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewComment(Comment newComment) {
        if (commentService.createNewComment(newComment) > 0) {
            return Response.status(HttpStatus.SC_OK).build();
        }
        return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/getComments")
    @Produces("application/json")
    public Response getComments() {
        List<Comment> listComment = commentService.fetchAllComment();
        if (listComment.isEmpty()) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        return Response.status(HttpStatus.SC_OK).entity(listComment).build();
    }

    @GET
    @Path("/getCommentById/{id}")
    @Produces("application/json")
    public Response getCommentById(@PathParam("id") BigInteger id) {
        Comment currComment = commentService.fetchCommentByID(id);
        if (currComment == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        return Response.status(HttpStatus.SC_OK).entity(currComment).build();
    }

    @PUT
    @Path("/updateComment/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateComment(@PathParam("id") BigInteger id, Comment newComment) {
        Comment currComment = commentService.fetchCommentByID(id);
        if (currComment == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        currComment.setComments(newComment.getComments());
        currComment.setCommentDate(newComment.getCommentDate());

        System.out.println("Result :" + commentService.updateCurrentComment(id, newComment));
        return Response.status(HttpStatus.SC_OK).build();
    }

    @DELETE
    @Path("/deleteComment/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") BigInteger id) {
        Comment currComment = commentService.fetchCommentByID(id);
        if (currComment == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        commentService.deleteComment(id);
        return Response.status(HttpStatus.SC_OK).build();
    }

}
