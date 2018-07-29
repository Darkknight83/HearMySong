package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.transfer.UserDTO;
import de.fh_dortmund.kosys.hear_my_song.web.backendadapter.Backendadapter;

@RequestScoped
@Path("/user")
public class UserEndpoint {

	@Inject
	Backendadapter backendadapter;

	/**
	 * Initiales Anlegen eines Nutzers auf dem Server
	 */
	@PUT
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response register(UserDTO user) {
		return Response.ok(backendadapter.register(user.getName(), user.getUserId(), user.getService(),
				user.getAccessToken(), user.getRefreshToken())).build();

	}

	/**
	 * Aktualisiert den accessToken eines Nutzers
	 * 
	 * @param accessToken
	 * @return
	 */
	@GET
	@Path("/refresh")
	public Response refreshToken(@FormParam("accessToken") String accessToken) {
		return null;
	}

}
