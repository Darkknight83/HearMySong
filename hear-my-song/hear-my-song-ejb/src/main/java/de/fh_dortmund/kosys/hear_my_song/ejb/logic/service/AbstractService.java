package de.fh_dortmund.kosys.hear_my_song.ejb.logic.service;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.exceptions.TokenExpiredException;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.services.ServiceModel;

public abstract class AbstractService {

	ServiceModel model;

	public AbstractService create(ServiceModel model) {
		this.model = model;
		this.login();
		return this;
	}

	public ServiceModel getModel() {
		return model;
	}

	protected void setModel(ServiceModel model) {
		this.model = model;
	}

	public abstract void login();

	public abstract void logoff();

	public void addSongToPlaylist(String songId, int position) throws TokenExpiredException {
		this.addSongToPlaylist(model.getPlaylistId(), songId);
	}

	protected abstract String createPlaylist(String name) throws TokenExpiredException;

	protected abstract void addSongToPlaylist(String playlistId, String songId) throws TokenExpiredException;

	public abstract String getUsername() throws TokenExpiredException;

	public abstract void refreshToken() throws TokenExpiredException;
}
