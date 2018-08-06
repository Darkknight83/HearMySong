package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.AbstractService;
import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.ServiceFactory;
import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.exceptions.TokenExpiredException;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;
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
	public String register(String name, String userId, long serviceId, String accessToken, String refreshToken)
			throws IllegalAccessException {
		if (name == null || accessToken == null || refreshToken == null) {
			throw new IllegalArgumentException();
		}
		User user = new User();
		user.setName(name);

		ServiceModel serviceModel = new ServiceModel();
		serviceModel.setAccessToken(accessToken);
		serviceModel.setRefreshToken(refreshToken);
		serviceModel.setUsername(name);
		serviceModel.setUserId(userId);

		Service service = serviceManagement.getService(serviceId);
		if (service == null) {
			throw new EntityNotFoundException("Service: " + serviceId);
		}

		AbstractService abstractService = ServiceFactory.getService(service, serviceModel);
		try {
			if (!abstractService.getUsername().equals(userId)) {
				throw new IllegalAccessException("Angegebener Username nicht korrekt!");
			}
		} catch (TokenExpiredException e) {
			logger.info("Token ungültig. Versuche zu refreshen!");
			try {
				abstractService.refreshToken();
				if (!abstractService.getUsername().equals(userId)) {
					throw new IllegalAccessException("Angegebener Username nicht korrekt!");
				}
			} catch (TokenExpiredException e1) {
				logger.error("Token konnte nicht refreshed werden!");
				throw new RuntimeException(e1);
			}
		}

		serviceCache.putService(user, abstractService);
		em.persist(user);
		logger.info(user + "erfolgreich registeriert");
		return abstractService.getModel().getAccessToken();
	}

}
