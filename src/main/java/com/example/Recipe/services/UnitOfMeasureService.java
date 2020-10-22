package com.example.Recipe.services;

import java.util.Set;

import com.example.Recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	public Set<UnitOfMeasureCommand> getUomList();
	
}
