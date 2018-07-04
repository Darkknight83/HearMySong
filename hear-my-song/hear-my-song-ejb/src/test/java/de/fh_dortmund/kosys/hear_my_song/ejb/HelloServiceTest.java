package de.fh_dortmund.kosys.hear_my_song.ejb;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloServiceTest {

	private HelloService helloService;

	@Before
	public void setUp() {
		HelloServiceImpl service = new HelloServiceImpl();
		helloService = service;
	}

	@Test
	public void testGreet() {
		assertEquals("Hello Mike", helloService.greet("Mike"));
		assertEquals("Hello World", helloService.greet(""));
		assertEquals("Hello World", helloService.greet(null));
	}

	@After
	public void tearDown() {
		helloService = null;
	}

}
