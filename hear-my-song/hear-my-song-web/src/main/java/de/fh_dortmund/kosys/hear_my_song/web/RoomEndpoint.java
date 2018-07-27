package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/room")
public class RoomEndpoint {

	public Response addSong(String roomId, String songId) {
		return null;
	}

	public Response getSongs() {
		return null;
	}
	
	public Response getRooms() {
		return null;
	}

	public Response getRoom() {
		return null;
	}

	public Response enterRoom() {
		return null;
	}
	
	public Response leaveRoom() {
		return null;
	}

}
