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
	public void enterRoom(User user, long roomId)
	{
		Room room = em.find(Room.class, roomId);
		room.addUser(user);
		em.merge(room);
	}
	
	@Override
	public void leaveRoom(User user,long roomId)
	{
		Room room = em.find(Room.class, roomId);
		room.removeUser(user);
		em.merge(room);
	}
	
	@Override
	public void addSong(Song song,long roomId)
	{
		Room room = em.find(Room.class, roomId);
		room.addSong(song);
		em.merge(room);
	}

	@Override
	public List<Song> getSongs(long roomId) {
		Room room = em.find(Room.class, roomId);
		return room.getSongs();
	}
	
	

}
