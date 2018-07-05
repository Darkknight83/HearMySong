package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = User.FIND_BY_NAME_LASTNAME, query = "SELECT u FROM User u WHERE u.nachname = :nachname") })
public class User {

	public static final String FIND_BY_NAME_LASTNAME = "UserFindByNameLastname";
	public static final String PARAMETER_NAME = " UserParameterName";
	public static final String PARAMETER_NACHNAME = "UserParameterNachname";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String nachname;

	public User() {
	}

	public User(String name, String nachname) {
		this.name = name;
		this.nachname = nachname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
}
