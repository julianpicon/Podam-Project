package com.tutorial.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tutorial.entity.Company;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class CompanyRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(CompanyRepositoryTest.class);
	private static CompanyRepository repository;

	@BeforeClass
	public static void beforeClass() throws SQLException {
		log.info("Initializing entity manager factory...");
		repository = new CompanyRepositoryImpl();
		Connection connection = repository.getEntityManager().unwrap(SessionImpl.class).connection();
		Database database = null;
		Liquibase liquibase = null;

		try {
			log.debug("Starting liquibase migration...");
			database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			liquibase = new Liquibase("dbChangelog.xml", new ClassLoaderResourceAccessor(), database);
			liquibase.update("test");
		} catch (LiquibaseException e) {
			log.error("Error occured in execution: {}", e.getMessage());
			e.printStackTrace();
		}
		log.info("Entity manager factory started");
	}

	@Before
	public void before() {

	}

	@Test
	public void testCreate() {
		
		PodamFactory factory = new PodamFactoryImpl();
		Company company = factory.manufacturePojo(Company.class);
		System.out.println(company);

		Optional<Company> p = repository.create(company);
		p.ifPresent(consumer -> {
			log.info("Created company: {}", consumer);
			assertNotNull(company.getIdCompany());
		});

	}

	@Test
	public void testUpdate() {
		Optional<Company> company = repository.read(1L);
		company.ifPresent(consumer -> {
			consumer.setPhone("123456");
			repository.update(consumer);
		});

		company = repository.read(1L);
		assertTrue(company.isPresent());

		assertNotNull(company.get().getName());

	}

	@Test
	public void testRead() {
		Optional<Company> company = repository.read(1L);

		assertTrue(company.isPresent());
		assertNotNull(company.get().getName());
	}

	@Test
	public void testDelete() {
		Optional<Company> company = repository.read(2L);

		company.ifPresent(consumer -> {
			assertNotNull(consumer);
			repository.delete(consumer);
		});

		company = repository.read(2L);
		assertFalse(company.isPresent());
	}

	@AfterClass
	public static void afterClass() {
		log.info("Closing entity manager factory...");
		repository.close();
		log.info("Entity manager factory closed");
	}
}
