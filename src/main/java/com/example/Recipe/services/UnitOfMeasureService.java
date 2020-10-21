package com.example.Recipe.services;

import java.util.ArrayList;
import java.util.List;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	public List<UnitOfMeasureCommand> getUomList();
	
}
