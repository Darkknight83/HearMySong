package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roomId")
	private long id;
	@OneToMany(mappedBy="room")
	private List<User> users;
	@OneToOne
	private Playlist playlist;
	
	public long getId()
	{
		return this.id;
	}
	
	public Room()
	{
		this.playlist = new Playlist();
	}
	
	public void addUser(User user)
	{
		this.users.add(user);
	}
	
	public void removeUser(User user)
	{
		this.users.remove(user);
	}
	
	public void addSong(Song song)
	{
		this.playlist.addSong(song);
	}
	
	public void removeSong(Song song)
	{
		this.playlist.removeSong(song);
	}
	
	public List<Song> getSongs()
	{
		return this.playlist.getSongs();
	}

}
