package de.fh_dortmund.kosys.hear_my_song.web.backendadapter;

import javax.inject.Inject;

import de.fh_dortmund.kosys.hear_my_song.ejb.UserManagemendLocal;

public class Backendadapter {

	@Inject
	private UserManagemendLocal userManagement;

	private Backendadapter() {

	}

	public String register(String name, String userId, long service, String accessToken, String refreshToken)
			throws IllegalAccessException {
		return this.userManagement.register(name, userId, service, accessToken, refreshToken);
	}

}