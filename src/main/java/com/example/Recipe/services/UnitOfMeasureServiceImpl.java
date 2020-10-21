package com.example.Recipe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.UnitOfMeasureCommand;
import com.example.Recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.Recipe.repositories.UnitOfMeasureRepository;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
	
	UnitOfMeasureRepository unitOfMeasureRepository;
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public List<UnitOfMeasureCommand> getUomList() {
		List<UnitOfMeasure> list = new ArrayList<UnitOfMeasure>();
		Iterable<UnitOfMeasure> iteableUom = unitOfMeasureRepository.findAll();
		
		return StreamSupport
				.stream(iteableUom.spliterator(), false)
				.map(unitOfMeasure->unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure))
				.collect(Collectors.toList());
	}
	
}
