package com.example.Recipe.services;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.Recipe.commands.UnitOfMeasureCommand;

@Component
public interface UnitOfMeasureService {

	public Set<UnitOfMeasureCommand> getUomList();
	
}
