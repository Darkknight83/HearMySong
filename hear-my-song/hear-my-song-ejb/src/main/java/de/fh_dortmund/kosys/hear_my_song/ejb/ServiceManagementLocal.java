package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Local;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Service;

@Local
public interface ServiceManagementLocal {

	public Service getService(long id);
}
