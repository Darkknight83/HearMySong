package de.fh_dortmund.kosys.hear_my_song.ejb.logic.service;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.services.ServiceModel;

public class ServiceFactory {

	public static AbstractService getService(Service model, ServiceModel userModel) {
		if (model.getName().equals("Spotify")) {
			return new SpotifyService().create(userModel);
		}
		throw new IllegalArgumentException();
	}
}
