package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = User.FIND_BY_NAME_LASTNAME, query = "SELECT u FROM User u") })
public class User {

	public static final String FIND_BY_NAME_LASTNAME = "UserFindByNameLastname";
	public static final String PARAMETER_NAME = " UserParameterName";
	public static final String PARAMETER_NACHNAME = "UserParameterNachname";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private long id;
	private String name;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Credentials> credentials;

	@ManyToOne
	@JoinColumn(name = "roomId")
	private Room room;

	@Embedded
	private UserSettings setting;

	public User(String name, Credentials credentials) {
		this.name = name;
		if (this.credentials == null)
			this.credentials = new ArrayList<>();
		this.credentials.add(credentials);
		if (setting == null)
			setting = new UserSettings();
	}

	public User() {
		setting = new UserSettings();
		this.credentials = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCredentials(Credentials credentials) {
		if (this.credentials == null)
			this.credentials = new ArrayList<>();
		this.credentials.add(credentials);

	}

	public List<Credentials> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Credentials> credentials) {
		this.credentials = credentials;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public UserSettings getSetting() {
		return setting;
	}

	public void setSetting(UserSettings setting) {
		this.setting = setting;
	}

	protected long getId() {
		return id;
	}

}
