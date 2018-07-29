package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.ServiceFactory;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Credentials;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.services.ServiceModel;

/**
 * Session Bean implementation class UserManagemendBean
 */
@Stateless
public class UserManagemendBean implements UserManagemendLocal {

	@Inject
	private transient Logger logger;

	@EJB
	ServiceManagementLocal serviceManagement;

	@EJB
	ServiceCacheBean serviceCache;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserManagemendBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String register(String name, String userId, long service, String accessToken, String refreshToken) {
		if (name == null || accessToken == null || refreshToken == null) {
			throw new IllegalArgumentException();
		}
		User user = new User();
		user.setName(name);
		user.addCredentials(
				new Credentials(user, serviceManagement.getService(service), accessToken, refreshToken, name));

		ServiceModel serviceModel = new ServiceModel();
		serviceModel.setAccessToken(accessToken);
		serviceModel.setRefreshToken(refreshToken);
		serviceModel.setUsername(name);
		serviceModel.setUserId(userId);

		serviceManagement.getService(service).getName();
		serviceCache.putService(user, ServiceFactory.getService(serviceManagement.getService(service), serviceModel));
		// TODO get those settings from Spotify
		user.getSetting().setCrossfade(0);
		user.getSetting().setShuffle(false);
		em.persist(user);
		logger.info(user + "erfolgreich registeriert");
		return accessToken;
	}

}
