package de.fh_dortmund.kosys.hear_my_song.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.fh_dortmund.kosys.hear_my_song.ejb.HelloService;

public class Backendadapter {

	private HelloService helloService;
	private static Backendadapter INSTANCE;

	private Backendadapter() {
		try {
			InitialContext jndiContext = new InitialContext();

			this.helloService = (HelloService) jndiContext.lookup(
					"java:global/hear-my-song-ear/de.fh-dortmund.kosys-hear-my-song-ejb-0.0.1-SNAPSHOT/HelloServiceImpl!de.fh_dortmund.kosys.hear_my_song.ejb.HelloService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String greet(String name) {
		return this.helloService.greet(name);
	}

	public boolean addUser(String name, String lastName) {
		return this.helloService.addUser(name, lastName);
	}

	public static Backendadapter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Backendadapter();
		}
		return INSTANCE;
	}

}