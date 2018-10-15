package com.project.ingsoft.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.ingsoft.exceptions.carrelloException;
import com.project.ingsoft.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class carrelloRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	
	
	@Test(expected = carrelloException.class)
	public void test_different_incorrect_inserts() throws carrelloException {
		entityManager.persist(new Carrello(-1,1));
	}
	

	@Test(expected = carrelloException.class)
	public void test_different_incorrect_inserts2() throws carrelloException {
		entityManager.persist(new Carrello(-1,-1));
	}
	

	@Test(expected = carrelloException.class)
	public void test_different_incorrect_inserts3() throws carrelloException {
		entityManager.persist(new Carrello(1,-1));
	}
	
	
}
