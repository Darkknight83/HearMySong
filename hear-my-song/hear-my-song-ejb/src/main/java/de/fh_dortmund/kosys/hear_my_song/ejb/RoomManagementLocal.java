package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.List;

import javax.ejb.Local;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

@Local
public interface RoomManagementLocal {

	public void createRoom();
	public void enterRoom(long userId, long roomId);
	public void leaveRoom(long userId,long roomId);
	public void addSong(long songId, long roomId);
	public void removeSong(long songId, long roomId);
	public List<Song> getSongs(long roomId);
}
