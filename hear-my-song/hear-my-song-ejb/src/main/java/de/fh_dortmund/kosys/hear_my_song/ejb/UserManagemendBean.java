package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Credentials;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

/**
 * Session Bean implementation class UserManagemendBean
 */
@Stateless
public class UserManagemendBean implements UserManagemendLocal {

	@Inject
	private transient Logger logger;

	@EJB
	ServiceManagementLocal serviceManagement;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserManagemendBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String register(String name, long service, String accessToken, String refreshToken) {
		User user = new User();
		user.setName(name);
		user.addCredentials(
				new Credentials(user, serviceManagement.getService(service), accessToken, refreshToken, name));
		em.persist(user);
		logger.info(user + "erfolgreich registeriert");
		return "asdsdf:wdfsdf";
	}

}
