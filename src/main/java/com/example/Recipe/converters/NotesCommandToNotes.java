package com.example.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.Notes;
import com.example.Recipe.commands.NotesCommand;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{

	@Override
	public Notes convert(NotesCommand source) {
		if(source==null)
		return null;
		
		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		return notes;
	}

	
}
