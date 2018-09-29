/*
 * Copyright 2016, Julius Krah                                                 
 * by the @authors tag. See the LICENCE in the distribution for a              
 * full listing of individual contributors.                                   
 *                                                                           
 * Licensed under the Apache License, Version 2.0 (the "License");             
 * you may not use this file except in compliance with the License.            
 * You may obtain a copy of the License at                                     
 * http://www.apache.org/licenses/LICENSE-2.0                                  
 * Unless required by applicable law or agreed to in writing, software         
 * distributed under the License is distributed on an "AS IS" BASIS,           
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.    
 * See the License for the specific language governing permissions and         
 * limitations under the License. 
 */
package com.tutorial.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tutorial.entity.Person;

public class PersonRepositoryImpl implements PersonRepository {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.juliuskrah.tutorial");
	private EntityManager em;

	public PersonRepositoryImpl() {
		em = emf.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Person> create(Person person) {
		Objects.requireNonNull(person, "Person must not be null");
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		return Optional.of(person);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Person> read(Long id) {
		em.getTransaction().begin();
		Person person = em.find(Person.class, id);
		em.getTransaction().commit();
		return Optional.ofNullable(person);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Person> update(Person person) {
		Objects.requireNonNull(person, "Person must not be null");
		em.getTransaction().begin();
		person = em.merge(person);
		em.getTransaction().commit();
		return Optional.of(person);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Person person) {
		em.getTransaction().begin();
		em.remove(person);
		em.getTransaction().commit();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Person> findAll() {
		em.getTransaction().begin();
		List<Person> list = em.createQuery("from Person p", Person.class).getResultList();
		em.getTransaction().commit();
		return list;
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
