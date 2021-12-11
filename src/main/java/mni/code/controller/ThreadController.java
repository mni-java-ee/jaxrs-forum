package mni.code.controller;

import mni.code.connection.DbHelper;
import mni.code.model.Thread;
import mni.code.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.sql.SQLException;
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
    public Response getThreadById(@PathParam("id")BigInteger id) throws SQLException {
       Thread currentThread = threadService.fetchThreadById(id);
       LOG.info("fetch thread by id", id);
        if (currentThread.getId() == null) {
            String msg = "record yang dipilih tidak ada";
            return Response.status(404).entity(msg).build();
        }
        return Response.status(200).entity(currentThread).build();
    }

    @GET
    @Path("/getThreads")
    @Produces("application/json")
    public Response getThreads() throws SQLException {
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
    public Response createNewThread(Thread newThread) throws SQLException {
        Thread thread = threadService.createNewThread(newThread);
        return Response.status(200).entity(thread).build();
    }

    @PUT
    @Path("/updateThread/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateThread(@PathParam("id") BigInteger id, Thread newThread) throws SQLException {
        String updatedThread = threadService.updateCurrentThread(id, newThread);
        return Response.status(200).entity(updatedThread).build();
    }

    @DELETE
    @Path("/deleteThread/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteThread(@PathParam("id") BigInteger id) throws SQLException {
        String result = threadService.deleteThreadById(id);
        return Response.status(200).entity(result).build();
    }
}
