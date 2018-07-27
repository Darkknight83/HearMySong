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

}
