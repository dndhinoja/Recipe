package com.example.Recipe.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> getUomList() {
		Set<UnitOfMeasure> list = new HashSet<UnitOfMeasure>();
		Iterable<UnitOfMeasure> iteableUom = unitOfMeasureRepository.findAll();
		
		return StreamSupport
				.stream(iteableUom.spliterator(), false)
				//.map(unitOfMeasure->unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure))
				.map(unitOfMeasureToUnitOfMeasureCommand::convert)
				.collect(Collectors.toSet());
	}
	
}
