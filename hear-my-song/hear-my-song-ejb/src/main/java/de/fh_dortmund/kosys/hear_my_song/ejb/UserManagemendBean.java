package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Credentials;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

/**
 * Session Bean implementation class UserManagemendBean
 */
@Stateless
public class UserManagemendBean implements UserManagemendLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserManagemendBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String register(String name, String service, String accessToken) {
		User user = new User();
		user.setName(name);
		user.addCredentials(new Credentials(user, new Service("Spotify", "http://Spotify"), accessToken, null, name));
		em.persist(user);
		return "asdsdf:wdfsdf";
	}

}
