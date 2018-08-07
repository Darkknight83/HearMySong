package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Local;

@Local
public interface UserManagemendLocal {

	public String register(String name, String userId, long service, String accessToken, String refreshToken)
			throws IllegalAccessException;
}