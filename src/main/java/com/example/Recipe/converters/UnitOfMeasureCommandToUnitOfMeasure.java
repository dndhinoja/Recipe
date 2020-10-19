package com.example.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.UnitOfMeasureCommand;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source==null)
		return null;
		
		final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(source.getId());
		unitOfMeasure.setUom(source.getUom());
		return unitOfMeasure;
		
	}

	
}
