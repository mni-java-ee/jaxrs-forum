package mni.code.controller;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
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
    	List<Thread> threads = dbHelper.getAllData();
    	return Response.status(200).entity(threads).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSingleData/{id}")
    public Response getSingleData(@PathParam("id") final String id) throws  SQLException {
        Integer idTarget = Integer.parseInt(id);
        List<Thread> threads = dbHelper.getSingleData(idTarget);
        if(threads.isEmpty()){
            return Response.status(400).entity(threads).build();
        }else{
            return Response.status(200).entity(threads).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateData/{id}")
    public Response updateData(@PathParam("id") final String id, Thread dataThread) throws SQLException {
        Integer idTarget = Integer.parseInt(id);
        String result = dbHelper.updateDatabyId(idTarget, dataThread);
        return Response.status(200).entity(result).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteData/{id}")
    public Response deleteData(@PathParam("id") final String id) throws SQLException {
        Integer idTarget = Integer.parseInt(id);
        String result = dbHelper.deleteById(idTarget);
        return Response.status(200).entity(result).build();
    }
}
