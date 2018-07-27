package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/service")
public class ServiceEndpoint {

	/**
	 * Liefert ein JSON mit allen Verfügbaren Services
	 * 
	 * @return
	 */
	@GET
	public Response getServices() {

		// TODO Fill with life
		return null;

	}

	/*
	 * Liefert ein JSON mit der Servicebeschreibung des gegebenen Services
	 * 
	 */
	@GET
	@Path("/{id}")
	public Response getService(@PathParam("id") String id) {
		// TODO Fill with life
		return null;
	}
}
