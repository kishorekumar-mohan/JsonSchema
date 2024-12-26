package validator;


import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class JsonSchema {
    public static void main(String[] args) {
        try {
            // Use FileInputStream to read the schema file
            InputStream schemaStream = new FileInputStream(new File("C:\\Users\\Kishore kumar\\eclipse-workspace\\schema\\src\\voter-schema.json"));
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);

            // Sample JSON to validate
            JSONObject jsonData = new JSONObject();
            jsonData.put("firstName", "Indra");
            jsonData.put("lastName", "Nehru");
            jsonData.put("age", 18);

            // Validate JSON
            schema.validate(jsonData);
            System.out.println("JSON is valid!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
