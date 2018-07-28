package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "songId")
	private long id;

	@ManyToOne
	private Service service;
	
	@ManyToOne
	private Playlist playlist;
	
	private String urlExtention;
	private long playbackTime;

	protected Service getService() {
		return service;
	}

	protected void setService(Service service) {
		this.service = service;
	}

	protected String getUrlExtention() {
		return urlExtention;
	}

	protected void setUrlExtention(String urlExtention) {
		this.urlExtention = urlExtention;
	}

	public long getPlaybackTime() {
		return playbackTime;
	}

	protected void setPlaybackTime(long playbackTime) {
		this.playbackTime = playbackTime;
	}

	public long getId() {
		return id;
	}
	
	public Playlist getPlaylist()
	{
		return this.playlist;
	}
	
	protected void setPlaylist(Playlist playlist)
	{
		this.playlist = playlist;
	}
	
}
