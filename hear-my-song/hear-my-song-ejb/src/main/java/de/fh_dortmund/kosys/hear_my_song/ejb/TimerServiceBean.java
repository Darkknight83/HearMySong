package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.Song;

/**
 * Session Bean implementation class TimerServiceBean
 */
@Singleton
@LocalBean
public class TimerServiceBean {

	@Resource
	TimerService timerService;

	Map<Timer, Song> timerMap;

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
		
		//TODO delete song from Database
	}

}
