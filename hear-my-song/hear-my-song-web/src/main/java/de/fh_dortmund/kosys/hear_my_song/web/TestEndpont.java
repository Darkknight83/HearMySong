package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/test")
public class TestEndpont {
	@GET
	@Path("/{name}")
	public Response getMsg(@PathParam("name") String name) {

		String output = "Welcome   : " + name;

		return Response.status(200).entity(output).build();

	}
}
