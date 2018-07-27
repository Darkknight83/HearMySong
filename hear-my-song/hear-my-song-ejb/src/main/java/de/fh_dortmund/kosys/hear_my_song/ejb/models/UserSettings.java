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
}
