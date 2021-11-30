package mni.code.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class HelloController {
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
}
