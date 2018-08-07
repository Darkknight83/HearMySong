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
import de.fh_dortmund.kosys.hear_my_song.ejb.models.wrapper.ServiceCacheWraper;

/**
 * Hält die aktuelle Autorisierung der Nutzer. Die Daten werden alle 30 Minuten
 * auf verwendung geprüft. Wurden die Daten zwei mal hintereinander als nicht
 * genutzt eingestuft, werden die Daten gelöscht und der Nutzer kann nicht
 * weiter interagieren
 */
@Singleton
@LocalBean
@Startup
public class ServiceCacheBean {

	Map<String, ServiceCacheWraper> serviceMap;

	/**
	 * Default constructor.
	 */
	public ServiceCacheBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		serviceMap = new HashMap<>();
	}

	public void putService(User user, AbstractService service) {
		String accessToken = service.getModel().getAccessToken();
		serviceMap.put(accessToken, new ServiceCacheWraper(user, service, true));
	}

	public AbstractService getService(String accessToken) {
		serviceMap.get(accessToken).setActive(true);
		return serviceMap.get(accessToken).getService();
	}

	@Schedule(minute = "*/30", hour = "*")
	public void delUnused() {
		for (ServiceCacheWraper service : serviceMap.values()) {
			if (!service.isActive()) {
				serviceMap.remove(service.getService().getModel().getAccessToken());
			} else {
				service.setActive(false);
			}
		}

	}
}
