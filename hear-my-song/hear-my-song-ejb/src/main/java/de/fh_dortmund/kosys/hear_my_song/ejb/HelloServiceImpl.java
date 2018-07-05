package de.fh_dortmund.kosys.hear_my_song.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.fh_dortmund.kosys.hear_my_song.ejb.models.User;

/**
 * Session Bean implementation class HelloServiceImpl
 */
@Stateless(mappedName = "helloService")
public class HelloServiceImpl implements HelloService {

	private static String DEFAULT_NAME = "World";

	@PersistenceContext
	private EntityManager em;

	@Override
	public String greet(String name) {
		TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_NAME_LASTNAME, User.class);
		query.setParameter("nachname", name);
		List<User> result = query.getResultList();
		if (result.isEmpty() || result.size() > 1) {
			return null;
		}
		User user = result.get(0);
		String greeting = "Hallo " + user.getName() + " " + user.getNachname();
		return greeting;
	}

	@Override
	public boolean addUser(String name, String lastName) {
		TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_NAME_LASTNAME, User.class);
		query.setParameter("nachname", lastName);
		if (!query.getResultList().isEmpty()) {
			return false;
		}
		em.persist(new User(name, lastName));
		return true;
	}
}
