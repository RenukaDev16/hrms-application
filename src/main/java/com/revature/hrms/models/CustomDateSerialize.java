package com.revature.hrms.models;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerialize extends JsonSerializer<LocalDate>{

	private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
		
	}
	
	
	

}
