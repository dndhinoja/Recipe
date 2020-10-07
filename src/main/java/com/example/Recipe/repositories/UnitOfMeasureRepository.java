package com.example.Recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Recipe.Domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{
	Optional<UnitOfMeasure> findByUom(String uom);
}
