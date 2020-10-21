package com.example.Recipe.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.UnitOfMeasureCommand;

class UnitOfMeasureToUnitOfMeasureCommandTest {

	UnitOfMeasureToUnitOfMeasureCommand converter;
	
	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	void testConvert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(1L);
		unitOfMeasure.setUom("unit Of measure");
		
		UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);
		assertEquals(unitOfMeasure.getId(), unitOfMeasureCommand.getId());
		
	}

}
