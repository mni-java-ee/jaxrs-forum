package mni.code.controller;

import jakarta.inject.Inject;
import mni.code.model.Thread;
import mni.code.service.ThreadService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigInteger;

@Path("/threads")
public class ThreadController {

    @Inject
    private ThreadService threadService;

    @GET
    @Path("/getThreadById/{id}")
    @Produces("application/json")
    public Response getThreadById(@PathParam("id")BigInteger id){
       Thread currentThread = threadService.fetchThreadById(id);
        if (currentThread == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(currentThread).build();
    }
}
