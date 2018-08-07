package de.fh_dortmund.kosys.hear_my_song.web.backendadapter;

import java.util.List;

import javax.inject.Inject;

import de.fh_dortmund.kosys.hear_my_song.ejb.RoomManagementLocal;
import de.fh_dortmund.kosys.hear_my_song.ejb.UserManagemendLocal;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;

public class Backendadapter {

	@Inject
	private UserManagemendLocal userManagement;

	@Inject
	private RoomManagementLocal roomManagement;

	private Backendadapter() {

	}

	public String register(String name, String userId, long service, String accessToken, String refreshToken)
			throws IllegalAccessException {
		return this.userManagement.register(name, userId, service, accessToken, refreshToken);
	}

	public List<Room> getRooms() {
		return roomManagement.getRooms();
	}

}