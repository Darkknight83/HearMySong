package de.fh_dortmund.kosys.hear_my_song.web.backendadapter;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.fh_dortmund.kosys.hear_my_song.ejb.UserManagemendLocal;

public class Backendadapter {

	private UserManagemendLocal userManagement;
	private static Backendadapter INSTANCE;

	private Backendadapter() {
		try {
			InitialContext jndiContext = new InitialContext();

			this.userManagement = (UserManagemendLocal) jndiContext.lookup(
					"java:global/hear-my-song-ear/de.fh-dortmund.kosys-hear-my-song-ejb-0.0.1-SNAPSHOT/UserManagemendBean!de.fh_dortmund.kosys.hear_my_song.ejb.UserManagemendLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String register(String name, String userId, long service, String accessToken, String refreshToken) {
		return this.userManagement.register(name, userId, service, accessToken, refreshToken);
	}


//	@Produces
	public  Backendadapter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Backendadapter();
		}
		return INSTANCE;
	}

}