
package com.tutorial.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.tutorial.entity.Company;
import com.tutorial.entity.Person;


public interface CompanyRepository {

	Optional<Company> create(Company company);

	Optional<Company> read(Long id);

	Optional<Company> update(Company company);

	void delete(Company company);

	List<Company> findAll();

	EntityManager getEntityManager();

	void close();

}
