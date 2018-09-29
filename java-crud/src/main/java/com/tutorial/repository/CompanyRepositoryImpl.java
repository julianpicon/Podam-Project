package com.tutorial.repository;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tutorial.entity.Company;

public class CompanyRepositoryImpl implements CompanyRepository {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.juliuskrah.tutorial");
	private EntityManager em;

	public CompanyRepositoryImpl() {
		em = emf.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Company> create(Company company) {
		Objects.requireNonNull(company, "Company must not be null");
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
		return Optional.of(company);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Company> read(Long id) {
		em.getTransaction().begin();
		Company company = em.find(Company.class, id);
		em.getTransaction().commit();
		return Optional.ofNullable(company);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Company> update(Company company) {
		Objects.requireNonNull(company, "Company must not be null");
		em.getTransaction().begin();
		company = em.merge(company);
		em.getTransaction().commit();
		return Optional.of(company);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Company company) {
		em.getTransaction().begin();
		em.remove(company);
		em.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		emf.close();
	}

}
