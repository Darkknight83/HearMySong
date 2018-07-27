package de.fh_dortmund.kosys.hear_my_song.web;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/room")
public class RoomEndpoint {

	/**
	 * Hinzufügen eines Songs zur Playlist innerhalb eines Raums
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
	public Response getRooms() {
		return null;
	}

	/**
	 * Liefert Infos zu einem Raum
	 * @return
	 */
	public Response getRoom() {
		return null;
	}

	/**
	 * Fügt nutzer einem Raum hinzu und überträgt die dortige Playlist in das Spotify des Nutzers
	 * @return
	 */
	public Response enterRoom() {
		return null;
	}
	
	/**
	 * Verlässt einen Raum, sodass keine Songs mehr zur Playlist hinzugefügt werden
	 * @return
	 */
	public Response leaveRoom() {
		return null;
	}

}
