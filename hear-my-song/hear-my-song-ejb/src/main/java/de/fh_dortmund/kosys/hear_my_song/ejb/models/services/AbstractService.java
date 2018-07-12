package de.fh_dortmund.kosys.hear_my_song.ejb.models.services;

public abstract class AbstractService {

	private String accessToken;

	public abstract String login();

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	protected String getAccessToken() {
		return accessToken;
	}

	public abstract Object getUserData();
}
