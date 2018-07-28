package de.fh_dortmund.kosys.hear_my_song.ejb.models;

import javax.persistence.Embeddable;

@Embeddable
public class UserSettings {

	/**
	 * Liefert Sekunden Crossfade des Nutzers
	 */
	private int crossfade;
	/**
	 * Gibt an, ob der Nutzer shuffel aktiviert hat
	 */
	private boolean shuffle;

	protected int getCrossfade() {
		return crossfade;
	}

	protected void setCrossfade(int crossfade) {
		this.crossfade = crossfade;
	}

	protected boolean isShuffle() {
		return shuffle;
	}

	protected void setShuffle(boolean shuffle) {
		this.shuffle = shuffle;
	}

}
