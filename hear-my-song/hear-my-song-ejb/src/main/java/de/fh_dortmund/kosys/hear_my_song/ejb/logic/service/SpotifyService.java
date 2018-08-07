package de.fh_dortmund.kosys.hear_my_song.ejb.logic.service;

import java.io.IOException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.exceptions.detailed.UnauthorizedException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;

import de.fh_dortmund.kosys.hear_my_song.ejb.logic.service.exceptions.TokenExpiredException;

public class SpotifyService extends AbstractService {

	private SpotifyApi spotifyApi;

	@Override
	public void login() {
		spotifyApi = new SpotifyApi.Builder().setAccessToken(this.getModel().getAccessToken())
				.setRefreshToken(this.getModel().getRefreshToken()).setClientId(System.getProperty("spotifyID"))
				.setClientSecret(System.getProperty("spotifySecret")).build();
	}

	@Override
	public void logoff() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addSongToPlaylist(String playlistId, String songId) throws TokenExpiredException {
		if (model.getPlaylistId() == null) {
			createPlaylist("Awesome");
		}
		spotifyApi.addTracksToPlaylist(this.model.getUserId(), playlistId, new String[] { songId });
	}

	@Override
	protected String createPlaylist(String name) throws TokenExpiredException {
		final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(model.getUserId(), name)
				.collaborative(false).public_(false).description("Playlist für Hear My Song").build();
		try {
			final Playlist playlist = createPlaylistRequest.execute();
			model.setPlaylistId(playlist.getId());
			return playlist.getId();

		} catch (IOException | SpotifyWebApiException e) {
			if (e instanceof UnauthorizedException) {
				throw new TokenExpiredException(e);
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUsername() throws TokenExpiredException {
		User user = null;
		try {
			user = spotifyApi.getCurrentUsersProfile().build().execute();
		} catch (SpotifyWebApiException | IOException e) {
			if (e instanceof UnauthorizedException) {
				throw new TokenExpiredException(e);
			}
			e.printStackTrace();
		}
		if (user == null) {
			throw new TokenExpiredException();
		}
		return user.getId();
	}

	@Override
	public void refreshTokenImpl() throws TokenExpiredException {
		try {
			AuthorizationCodeCredentials credentials = spotifyApi.authorizationCodeRefresh().build().execute();
			this.model.setAccessToken(credentials.getAccessToken());
			this.model.setRefreshToken(credentials.getRefreshToken());
			spotifyApi.setAccessToken(credentials.getAccessToken());
			spotifyApi.setRefreshToken(credentials.getRefreshToken());

		} catch (SpotifyWebApiException | IOException e) {
			if (e instanceof UnauthorizedException) {
				throw new TokenExpiredException(e);
			}
			e.printStackTrace();
		}

	}

}
