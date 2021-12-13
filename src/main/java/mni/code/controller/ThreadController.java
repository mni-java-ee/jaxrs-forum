package mni.code.controller;

import mni.code.model.Thread;
import mni.code.service.ThreadService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Path("/thread-mgmt")
public class ThreadController {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadController.class);

    @Inject
    private ThreadService threadService;

    @GET
    @Path("/getThreadById/{id}")
    @Produces("application/json")
    public Response getThreadById(@PathParam("id") BigInteger id) {
        Thread thread = threadService.fetchThreadById(id);
        if (thread == null){
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        return Response.status(HttpStatus.SC_OK).entity(thread).build();
    }

    @GET
    @Path("/getThreads")
    @Produces("application/json")
    public Response getThreads() {
        List<Thread> threads = threadService.fetchAllThread();
        if (threads.isEmpty()) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(threads).build();
    }

    @POST
    @Path("/createNewThread")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewThread(Thread newThread) {
        if (threadService.createNewThread(newThread) > 0) {
            return Response.status(HttpStatus.SC_OK).build();
        }
        return Response.status(HttpStatus.SC_NOT_FOUND).build();
    }

    @PUT
    @Path("/updateThread/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateThread(@PathParam("id") BigInteger id, Thread newThread) {
        Thread thread = threadService.fetchThreadById(id);
        if (newThread == null) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
        thread.setThreadName(newThread.getThreadName());
        thread.setThreadContent(newThread.getThreadContent());
        thread.setThreadDate(newThread.getThreadDate());

        threadService.updateCurrentThread(id, newThread);
        return Response.status(HttpStatus.SC_OK).build();
    }

}
