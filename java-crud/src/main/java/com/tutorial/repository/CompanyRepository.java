
package com.tutorial.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.tutorial.entity.Company;

public interface CompanyRepository {

	Optional<Company> create(Company company);

	Optional<Company> read(Long id);
	
	Optional<Company> update(Company company);

	void delete(Company company);

	EntityManager getEntityManager();

	void close();

}
