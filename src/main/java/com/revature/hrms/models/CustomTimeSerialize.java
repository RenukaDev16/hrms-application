package com.revature.hrms.models;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomTimeSerialize  extends JsonSerializer<LocalTime>{
 DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
		
	}

}
