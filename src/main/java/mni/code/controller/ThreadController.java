package mni.code.controller;

import jakarta.inject.Inject;
import mni.code.model.Thread;
import mni.code.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.LinkedList;

@Path("/thread-mgmt")
public class ThreadController {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadController.class);

    @Inject
    private ThreadService threadService;

    @GET
    @Path("/getThreadById/{id}")
    @Produces("application/json")
    public Response getThreadById(@PathParam("id")BigInteger id){
       Thread currentThread = threadService.fetchThreadById(id);
       LOG.info("fetch thread by id", id);
        if (currentThread == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(currentThread).build();
    }

    @GET
    @Path("/getThreads")
    @Produces("application/json")
    public Response getThreads(){
        LinkedList<Thread> threads = threadService.fetchAllThread();
        if (threads.isEmpty()) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(threads).build();
    }

    @POST
    @Path("/createNewThread")
    @Consumes("application/json")
    public Response createNewThread(Thread newThread){
        Thread thread = threadService.createNewThread(newThread);
        return Response.status(200).entity(thread).build();
    }
}
