package de.fh_dortmund.kosys.hear_my_song.web.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model to store data for the UserEndpoint
 * 
 * @author timpr
 *
 */
@JsonInclude(Include.NON_DEFAULT)
public class UserDTO {

	private String name;
	private String userId;
	private long service;
	private String accessToken;
	private String refreshToken;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getService() {
		return service;
	}

	public void setService(long service) {
		this.service = service;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
