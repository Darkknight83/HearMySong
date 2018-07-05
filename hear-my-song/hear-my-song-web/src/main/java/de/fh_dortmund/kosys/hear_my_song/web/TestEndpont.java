package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/test")
public class TestEndpont {
	@GET
	@Path("/{name}")
	public Response getMsg(@PathParam("name") String name) {

		String output = Backendadapter.getInstance().greet(name);

		if (output == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(output).build();

	}

	@PUT
	@Path("/add/{name}/{lastname}")
	public Response addUser(@PathParam("name") String name, @PathParam("lastname") String lastname) {

		if (Backendadapter.getInstance().addUser(name, lastname)) {
			return Response.status(201).build();
		}
		return Response.status(404).build();
	}
}
