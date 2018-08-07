package de.fh_dortmund.kosys.hear_my_song.ejb.models.wrapper;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.AbstractService;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

public class ServiceCacheWraper {

	private User user;
	private AbstractService service;
	private boolean active;

	public ServiceCacheWraper(User user, AbstractService service, boolean active) {
		this.user = user;
		this.service = service;
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AbstractService getService() {
		return service;
	}

	public void setService(AbstractService service) {
		this.service = service;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
