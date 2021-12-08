package mni.code.controller;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mni.code.connection.DbHelper;
import mni.code.model.Thread;

@Path("/hello")
public class HelloController {
	
	@Inject
	private DbHelper dbHelper;
	
    @GET
    @Path("/world")
    public String helloworld() {
        return "Hello World!";
    }

    @GET
    @Path("/helloName/{name}")
    public String hello(@PathParam("name") final String name) {
        return "Hello " +name;
    }
    
    @GET
    @Path("/helloDB")
    public String connDB() {
    	String responses = "";
    	responses = dbHelper.tesKoneksi();
    	return responses;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/insertData")
    public Response insertData(Thread newThread) throws SQLException {
    	String responses = "tidak ada responses";    
    	responses = dbHelper.insertData(newThread);
    	return Response.status(200).entity(responses).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllData")
    public Response getAllData() throws SQLException {
    	dbHelper.getAllData();
    	List<Thread> threads = dbHelper.getThreadList();
    	return Response.status(200).entity(threads).build();
    }
}
