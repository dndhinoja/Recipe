package com.example.Recipe;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	//@DirtiesContext
	public void testFindByUom() throws Exception{
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");
		assertEquals("Teaspoon", unitOfMeasure.get().getUom());
	}
	@Test
	public void testFindByUomCup() throws Exception{
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Tablespoon");
		assertEquals("Tablespoon", unitOfMeasure.get().getUom());
	}

}
