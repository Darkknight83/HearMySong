package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fh_dortmund.kosys.hear_my_song.web.backendadapter.Backendadapter;

@RequestScoped
@Path("/room")
public class RoomEndpoint {

	@Inject
	Backendadapter backendadapter;

	/**
	 * Hinzufügen eines Songs zur Playlist innerhalb eines Raums
	 * 
	 * @param roomId
	 * @param songId
	 * @return
	 */
	public Response addSong(String roomId, String songId) {
		return null;
	}

	/**
	 * Liefert alle Songs in einem Raum
	 */
	public Response getSongs() {
		return null;
	}

	/**
	 * Liefer alle Räume
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRooms() {
		return Response.ok().entity(backendadapter.getRooms()).build();
	}

	/**
	 * Liefert Infos zu einem Raum
	 * 
	 * @return
	 */
	public Response getRoom() {
		return null;
	}

	/**
	 * Fügt nutzer einem Raum hinzu und überträgt die dortige Playlist in das
	 * Spotify des Nutzers. Deaktiviert shuffle und crossfade des nutzers
	 * 
	 * @return
	 */
	public Response enterRoom() {
		return null;
	}

	/**
	 * Verlässt einen Raum, sodass keine Songs mehr zur Playlist hinzugefügt werden
	 * 
	 * @return
	 */
	public Response leaveRoom() {
		return null;
	}

	/**
	 * Erstellt einen neuen Raum
	 * 
	 * @return
	 */
	public Response createRoom() {
		return null;
	}

	/**
	 * Synchronisiert die Playbackzeit mit der auf dem Server
	 * 
	 * @return
	 */
	public Response syncPlayback() {
		return null;
	}

}
