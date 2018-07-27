package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Credentials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credentialId")
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "serviceId")
	private Service service;
	private String token;
	private String refreshToken;
	private String serviceUserId;

	public Credentials() {
	}

	public Credentials(User user, Service service, String token, String refreshToken, String userId) {
		this.user = user;
		this.service = service;
		this.token = token;
		this.refreshToken = refreshToken;
		this.serviceUserId = userId;
	}

	protected User getUser() {
		return user;
	}

	protected void setUser(User user) {
		this.user = user;
	}

	protected Service getService() {
		return service;
	}

	protected void setService(Service service) {
		this.service = service;
	}

	protected String getToken() {
		return token;
	}

	protected void setToken(String token) {
		this.token = token;
	}

	protected String getRefreshToken() {
		return refreshToken;
	}

	protected void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	protected String getServiceUserId() {
		return serviceUserId;
	}

	protected void setServiceUserId(String userId) {
		this.serviceUserId = userId;
	}

}
