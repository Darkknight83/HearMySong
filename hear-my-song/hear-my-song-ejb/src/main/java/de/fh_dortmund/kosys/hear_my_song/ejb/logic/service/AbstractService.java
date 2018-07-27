package de.fh_dortmund.kosys.hear_my_song.ejb.logic.service;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.services.ServiceModel;

public abstract class AbstractService {

	ServiceModel model;

	public AbstractService create(ServiceModel model) {
		this.model = model;
		return this;
	}

	protected ServiceModel getModel() {
		return model;
	}

	protected void setModel(ServiceModel model) {
		this.model = model;
	}

	public abstract String login();
	
	public abstract void logoff();

	public void addSongToPlaylist(String songId, int position) {
		this.addSongToPlaylist(model.getPlaylistId(), songId, position);
	}

	protected abstract String createPlaylist(String name);

	protected abstract void addSongToPlaylist(String playlistId, String songId, int position);
}
