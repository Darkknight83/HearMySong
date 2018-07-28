package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import java.util.List;

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

	@OneToMany(mappedBy = "user")
	private List<Credentials> credentials;

	@ManyToOne
	@JoinColumn(name = "roomId")
	private Room room;

	@Embedded
	private UserSettings setting;

	public User(String name, Credentials credentials) {
		this.name = name;
		this.credentials.add(credentials);
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCredentials(Credentials credentials) {
		this.credentials.add(credentials);

	}

	protected List<Credentials> getCredentials() {
		return credentials;
	}

	protected void setCredentials(List<Credentials> credentials) {
		this.credentials = credentials;
	}

	protected Room getRoom() {
		return room;
	}

	protected void setRoom(Room room) {
		this.room = room;
	}

	protected UserSettings getSetting() {
		return setting;
	}

	protected void setSetting(UserSettings setting) {
		this.setting = setting;
	}

	protected long getId() {
		return id;
	}

}
