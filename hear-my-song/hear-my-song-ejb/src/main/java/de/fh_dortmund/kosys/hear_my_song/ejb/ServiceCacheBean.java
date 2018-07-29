package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.AbstractService;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

/**
 * Session Bean implementation class ServiceCacheBean
 */
@Singleton
@LocalBean
@Startup
public class ServiceCacheBean {

	Map<User, AbstractService> serviceMap;
	Map<User, Boolean> isActive;

	/**
	 * Default constructor.
	 */
	public ServiceCacheBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		serviceMap = new HashMap<>();
		isActive = new HashMap<>();
	}

	public void putService(User user, AbstractService service) {
		serviceMap.put(user, service);
		isActive.put(user, true);
	}

	public AbstractService getService(User user) {
		return serviceMap.get(user);
	}

	@Schedule(minute = "*/30", hour = "*")
	public void delUnused() {
		for (User user : serviceMap.keySet()) {
			if (!isActive.get(user)) {
				serviceMap.remove(user);
			}
		}

	}
}
