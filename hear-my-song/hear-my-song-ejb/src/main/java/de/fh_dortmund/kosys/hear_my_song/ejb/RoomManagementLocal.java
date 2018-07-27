package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.List;

import javax.ejb.Local;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

@Local
public interface RoomManagementLocal {

	public void createRoom();
	public void enterRoom(User user, long roomId);
	public void leaveRoom(User user,long roomId);
	public void addSong(Song song, long roomId);
	public List<Song> getSongs(long roomId);
}
