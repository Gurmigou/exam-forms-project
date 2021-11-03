package com.infpulse.studentspoll.db;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class DBTestSetup {

	protected static final PostgreSQLContainer postgreSQLContainer;
	static {
		postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14-alpine3.14")
				.withUsername("testuser")
				.withPassword("testp@ssw0rd")
				.withReuse(true);
		postgreSQLContainer.start();
	}

	@DynamicPropertySource
	public static void setDatasourceProperties (final DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}
}
