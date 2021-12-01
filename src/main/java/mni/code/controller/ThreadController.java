package mni.code.controller;

import mni.code.model.Thread;
import mni.code.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewThread(Thread newThread){
        Thread thread = threadService.createNewThread(newThread);
        return Response.status(200).entity(thread).build();
    }

    @PUT
    @Path("/updateThread/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateThread(@PathParam("id") BigInteger id, Thread newThread){
        Thread currentThread = threadService.fetchThreadById(id);
        if (currentThread == null) {
            return Response.status(404).entity(currentThread).build();
        }

        currentThread.setThreadContent(newThread.getThreadContent());
        currentThread.setThreadName(newThread.getThreadName());
        currentThread.setThreadDate(newThread.getThreadDate());

        List<Thread> threads = threadService.updateCurrentThread(id, currentThread);
        return Response.status(200).entity(threads).build();
    }
}
