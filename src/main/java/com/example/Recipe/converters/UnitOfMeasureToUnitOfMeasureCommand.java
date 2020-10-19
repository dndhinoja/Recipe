package com.example.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.UnitOfMeasureCommand;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source==null)
		return null;
		
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(source.getId());
		unitOfMeasureCommand.setUom(source.getUom());
		return unitOfMeasureCommand;
	}

}
