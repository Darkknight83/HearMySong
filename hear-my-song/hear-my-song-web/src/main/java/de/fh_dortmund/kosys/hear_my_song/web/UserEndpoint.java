package de.fh_dortmund.kosys.hear_my_song.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import de.fh_dortmund.kosys.hear_my_song.web.backendadapter.Backendadapter;
import de.fh_dortmund.kosys.hear_my_song.web.models.UserDTO;

@RequestScoped
@Path("/user")
public class UserEndpoint {

	@Inject
	Backendadapter backendadapter;

	@Inject
	private static Logger logger;

	/**
	 * Initiales Anlegen eines Nutzers auf dem Server
	 */
	@PUT
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@Context HttpHeaders headers, UserDTO user) {
		try {
			List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
			String authString = authHeaders.get(0);
			if (!authString.startsWith("Bearer ")) {
				throw new IllegalArgumentException("Token hat das falsche Format");
			}
			String token = authString.substring(authString.indexOf(' ') + 1);
			System.out.println("AccesToken: " + token);
			String accessToken = backendadapter.register(user.getName(), user.getUserId(), user.getService(),
					user.getAccessToken(), user.getRefreshToken());
			UserDTO userReturn = new UserDTO();
			userReturn.setAccessToken(accessToken);
			return Response.ok().entity(userReturn).build();
		} catch (Exception e) {
//			logger.error("Fehler in der Veraerbeitung", e);
			return Response.serverError().entity(e.getMessage()).build();
		}

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
