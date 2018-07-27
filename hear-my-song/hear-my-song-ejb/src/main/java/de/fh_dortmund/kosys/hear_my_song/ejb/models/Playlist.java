package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="playlistId")
	private long id;
	@OneToOne
	private Room room;
	@OneToMany
	@JoinColumn(name="playlistId")
	private List<Song> songs;

	public void addSong(Song song)
	{
		songs.add(song);
	}
	
	public void removeSong(Song song)
	{
		songs.remove(song);
	}
	
	public List<Song> getSongs()
	{
		return this.songs;
	}
}
