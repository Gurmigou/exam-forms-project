package com.infpulse.studentspoll.db;

import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends DBTestSetup{

	protected final UserRepository userRepository;

	protected final TestEntityManager entityManager;

	@Autowired
	public UserRepositoryTest(UserRepository userRepository, TestEntityManager entityManager) {
		this.userRepository = userRepository;
		this.entityManager = entityManager;
	}

	@Test
	public void shouldFindNoUsersIfRepoIsEmpty() {
		Iterable<User> users = userRepository.findAll();

		assertThat(users).isEmpty();
	}

	@Test
	public void shouldStoreUser() {
		User user = User.builder()
				.name("user0")
				.surname("surname0")
				.login("login0")
				.password("passw0rd")
//				.isDeleted(false)
				.build();

		User savedUser = userRepository.save(user);
		assertThat(savedUser).hasFieldOrPropertyWithValue("name", "user0");
		assertThat(savedUser).hasFieldOrPropertyWithValue("surname", "surname0");
		assertThat(savedUser).hasFieldOrPropertyWithValue("login", "login0");
		assertThat(savedUser).hasFieldOrPropertyWithValue("password", "passw0rd");
		assertThat(savedUser).hasFieldOrPropertyWithValue("isDeleted", false);
	}

	@Test
	public void shouldFindAllUsers() {
		User user1 = User.builder()
				.name("user1")
				.surname("surname1")
				.login("login1")
				.password("passw0rd")
				.build();
		entityManager.persist(user1);

		User user2 = User.builder()
				.name("user2")
				.surname("surname2")
				.login("login2")
				.password("passw0rd")
				.build();
		entityManager.persist(user2);

		User user3 = User.builder()
				.name("user3")
				.surname("surname3")
				.login("login3")
				.password("passw0rd")
				.build();
		entityManager.persist(user3);

		Iterable<User> users = userRepository.findAll();

		assertThat(users).hasSize(3).contains(user1, user2, user3);
	}

	@Test
	public void shouldFindUserByLogin() {
		User user1 = User.builder()
				.name("user1")
				.surname("surname1")
				.login("login1")
				.password("passw0rd")
				.build();
		entityManager.persist(user1);

		User user2 = User.builder()
				.name("user2")
				.surname("surname2")
				.login("login2")
				.password("passw0rd")
				.build();
		entityManager.persist(user2);

		User user3 = User.builder()
				.name("user3")
				.surname("surname3")
				.login("login3")
				.password("passw0rd")
				.build();
		entityManager.persist(user3);

		User user = userRepository.findByLogin(user2.getLogin()).get();

		assertThat(user.getId()).isEqualTo(user2.getId());
		assertThat(user.getName()).isEqualTo(user2.getName());
		assertThat(user.getSurname()).isEqualTo(user2.getSurname());
		assertThat(user.getLogin()).isEqualTo(user2.getLogin());
		assertThat(user.getPassword()).isEqualTo(user2.getPassword());
	}

}
