package mni.code.controller;

import mni.code.model.Comment;
import mni.code.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment-mgmt")
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Inject
    private CommentService commentService;

    @POST
    @Path("/createNewComment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewComment(Comment newComment){
        Comment comment = commentService.createNewComment(newComment);
        return Response.status(200).entity(comment).build();
    }
}
