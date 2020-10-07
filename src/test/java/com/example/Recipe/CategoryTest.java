package com.example.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.example.Recipe.Domain.Category;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	@Test
	public void testGetId() {
		Long value = 4L;
		category.setId(value);
		assertEquals(value, category.getId());
	}

	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfRecipe() {
		fail("Not yet implemented");
	}

}
