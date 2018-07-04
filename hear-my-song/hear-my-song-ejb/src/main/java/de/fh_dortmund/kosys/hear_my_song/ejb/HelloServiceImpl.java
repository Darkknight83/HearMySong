package de.fh_dortmund.kosys.hear_my_song.ejb;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

/**
 * Session Bean implementation class HelloServiceImpl
 */
@Stateless(mappedName = "helloService")
public class HelloServiceImpl implements HelloService {

	private static String DEFAULT_NAME = "World";

	@Override
	public String greet(String name) {
		String finalName = StringUtils.defaultIfEmpty(name, DEFAULT_NAME);
		String greeting = "Hello " + finalName;
		return greeting;
	}
}
