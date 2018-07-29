package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;

/**
 * Session Bean implementation class ServiceManagementBean
 */
@Stateless
public class ServiceManagementBean implements ServiceManagementLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ServiceManagementBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Service getService(long id) {
		return em.find(Service.class, id);
	}

}
