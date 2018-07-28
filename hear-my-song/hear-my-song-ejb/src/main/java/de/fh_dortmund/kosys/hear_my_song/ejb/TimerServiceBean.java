package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Room;
import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;

/**
 * Session Bean implementation class TimerServiceBean
 */
@Singleton
@LocalBean
public class TimerServiceBean {

	@EJB
	RoomManagementLocal roomManagement;
	
	@Resource
	TimerService timerService;

	Map<Timer, Song> timerMap;
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private transient Logger logger;

	/**
	 * Default constructor.
	 */
	public TimerServiceBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the Bean
	 */
	@PostConstruct
	private void init() {
		timerMap = new HashMap<>();
	}

	/**
	 * Timer for Playbacktime
	 * @param song
	 */
	public void setTimer(Song song) {
		Timer timer = timerService.createTimer(song.getPlaybackTime(), "Created new programmatic timer");
		timerMap.put(timer, song);
	}

	/**
	 * End of playback reached
	 * @param timer
	 */
	@Timeout
	public void playbackFinished(Timer timer) {
		Song song = timerMap.get(timer);
		roomManagement.removeSong(song.getId(), song.getPlaylist().getRoom().getId());
		timerMap.remove(timer);
	}

}
