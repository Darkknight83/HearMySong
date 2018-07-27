package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

/**
 * Session Bean implementation class RoomManagementBean
 */
@Stateless
public class RoomManagementBean implements RoomManagementLocal {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UserManagemendLocal userManagement;
    /**
     * Default constructor. 
     */
    public RoomManagementBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createRoom() {
		Room room = new Room();
		em.persist(room);
	}
	
	@Override
	public void enterRoom(User user, Room room)
	{
		room.addUser(user);
	}
	
	@Override
	public void leaveRoom(User user,Room room)
	{
		room.removeUser(user);
	}
	
	@Override
	public void addSong(Song song,Room room)
	{
		room.addSong(song);
	}

	@Override
	public List<Song> getSongs(Room room) {
		return room.getSongs();
	}
	
	

}
