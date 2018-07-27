package de.fh_dortmund.kosys.hear_my_song.ejb.logic.service;

import java.io.IOException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;

public class SpotifyService extends AbstractService {

	private SpotifyApi spotifyApi;


	@Override
	public String login() {
		spotifyApi = new SpotifyApi.Builder().setAccessToken(this.getModel().getAccessToken()).build();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoff() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addSongToPlaylist(String playlistId, String songId, int posistion) {
		if (model.getPlaylistId() == null) {
			createPlaylist("Awsome");
		}
	}

	@Override
	protected String createPlaylist(String name) {
		final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(model.getUserId(), name)
				.collaborative(false).public_(false).description("Playlist für Hear My Song").build();
		try {
			final Playlist playlist = createPlaylistRequest.execute();
			model.setPlaylistId(playlist.getId());
			return playlist.getId();

		} catch (IOException | SpotifyWebApiException e) {
			// TODO LOG
		}
		return null;
	}

}
