package mni.code.controller;

import javax.inject.Inject;
import javax.ws.rs.*;

import mni.code.connection.DbHelper;


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
