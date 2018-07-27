package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Stateless;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;

/**
 * Session Bean implementation class ServiceManagementBean
 */
@Stateless
public class ServiceManagementBean implements ServiceManagementLocal {

	/**
	 * Default constructor.
	 */
	public ServiceManagementBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Service getService(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
