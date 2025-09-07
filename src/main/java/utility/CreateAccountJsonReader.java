package utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to read data from JSON files using Jackson library. Supports
 * loading a JSON file and fetching values using nested keys.
 */
public class CreateAccountJsonReader {

	JsonNode jsonNode; // Holds the root JSON node after file is loaded

	/**
	 * Loads a JSON file from src/test/resources folder.
	 * 
	 * @param fileName - name of the JSON file (without .json extension)
	 */
	public void loadJson(String fileName) {
		String basePath = System.getProperty("user.dir"); // Get project root path
		String testResourceLocation = "\\src\\test\\resources\\"; // Location of resources folder
		String filePath = basePath + testResourceLocation + fileName + ".json"; // Full file path

		ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper to parse JSON
		try {
			jsonNode = mapper.readTree(new File(filePath)); // Load JSON file into JsonNode
		} catch (IOException e) {
			e.printStackTrace(); // Print error if file not found or invalid JSON
		}
	}

	/**
	 * Retrieves a value from the loaded JSON using one or more nested keys.
	 * 
	 * @param keys - sequence of keys to navigate through the JSON hierarchy
	 * @return value as String, or null if not found
	 */
	public String getValue(String... keys) {
		JsonNode tempNode = jsonNode; // Start from the root JSON node
		for (String key : keys) { // Loop through all given keys
			if (tempNode != null) {
				tempNode = tempNode.get(key); // Navigate deeper into JSON hierarchy
			}
		}
		return tempNode != null ? tempNode.asText() : null; // Return value or null if not found
	}
	
	/**
	 * Retrieves a value from the loaded JSON using one or more nested keys.
	 * 
	 * @param keys
	 * @return
	 */
	 public JsonNode getJsonNode(String... keys) {
	        JsonNode tempNode = jsonNode;
	        for (String key : keys) {
	            if (tempNode != null) {
	                tempNode = tempNode.get(key);
	            }
	        }
	        return tempNode;
	    }
}
