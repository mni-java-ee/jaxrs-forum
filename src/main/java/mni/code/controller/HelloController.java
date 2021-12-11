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
}
