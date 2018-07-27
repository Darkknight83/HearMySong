package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.List;

import javax.ejb.Local;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

@Local
public interface RoomManagementLocal {

	public void createRoom();
	public void enterRoom(User user, Room room);
	public void leaveRoom(User user,Room room);
	public void addSong(Song song, Room room);
	public List<Song> getSongs(Room room);
}
