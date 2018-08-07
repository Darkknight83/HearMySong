package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;

/**
 * Session Bean implementation class TimerServiceBean
 */
@Singleton
@LocalBean
@Startup
public class TimerServiceBean {

	@EJB
	RoomManagementLocal roomManagement;

	@Resource
	TimerService timerService;

	BiMap<Timer, Song> timerMap;

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
		timerMap = HashBiMap.create();
	}

	/**
	 * Timer for Playbacktime
	 * 
	 * @param song
	 */
	public void setTimer(Song song) {
		Timer timer = timerService.createTimer(song.getPlaybackTime(), "Created new programmatic timer");
		timerMap.put(timer, song);
	}

	public long getActualPlayback(Song song) {
		return song.getPlaybackTime() - timerMap.inverse().get(song).getTimeRemaining();
	}

	/**
	 * End of playback reached
	 * 
	 * @param timer
	 */
	@Timeout
	public void playbackFinished(Timer timer) {
		Song song = timerMap.get(timer);
		roomManagement.removeSong(song.getId(), song.getPlaylist().getRoom().getId());
		timerMap.remove(timer);
	}

}
