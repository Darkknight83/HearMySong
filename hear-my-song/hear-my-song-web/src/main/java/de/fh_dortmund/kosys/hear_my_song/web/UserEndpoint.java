package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/user")
public class UserEndpoint {

	/**
	 * Initiales Anlegen eines Nutzers auf dem Server
	 */
	@PUT
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response register(@FormParam("name") String name, @FormParam("service") String service,
			@FormParam("accessToken") String accessToken, @FormParam("refreshToken") String refreshToken) {
		return Response
				.ok(Backendadapter.getInstance().register(name, Long.parseLong(service), accessToken, refreshToken))
				.build();

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
