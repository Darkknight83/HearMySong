package de.fh_dortmund.kosys.hear_my_song.util;

import java.util.ResourceBundle;

/**
 * Class used to read the project version from a maven filtered resource bundle. 
 */
public class Version {
	
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("hear-my-song-util");
	
	public static String VALUE = BUNDLE.getString("version"); 
}
