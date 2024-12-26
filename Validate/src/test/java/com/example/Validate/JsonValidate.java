package com.example.Validate;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class JsonValidate {
	
	public static void main(String[] args) {
		 
        String schemaPath = "C:\\Users\\Kishore kumar\\eclipse-workspace\\Validate\\src\\schema.json";
        String jsonPath = "C:\\Users\\Kishore kumar\\eclipse-workspace\\Validate\\src\\listInput.json";
       
        try {
            boolean isValid = validateJsonAgainstSchema(jsonPath, schemaPath);
            if (isValid) {
                System.out.println("Valid JSON!");
            } else {
                System.out.println("Invalid JSON!");
            }
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }}

private static boolean validateJsonAgainstSchema(String jsonPath, String schemaPath) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode jsonNode = mapper.readTree(new File(jsonPath));
    JsonNode schemaNode = mapper.readTree(new File(schemaPath));

    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    
    JsonSchema schema = factory.getSchema(schemaNode);

    Set<ValidationMessage> errors = schema.validate(jsonNode);

    if (errors.isEmpty()) {
        return true;
    } else {
    	 System.out.print("Validation Errors:");
        errors.forEach(error -> System.out.println(error.getMessage()));
        return false;
    }
}



}
