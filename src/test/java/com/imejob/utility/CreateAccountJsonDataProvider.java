package com.imejob.utility;

import java.util.stream.StreamSupport;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.JsonNode;

import utility.JsonReader;

public class CreateAccountJsonDataProvider {
	
	@DataProvider(name = "getCreateAccountData")
    public static Object[][] getCreateAccountData() {
        JsonReader reader = new JsonReader();
        reader.loadJson("createAccountData"); // JSON file name without .json

        // Get technicalSkills as array
        JsonNode skillsNode = reader.getJsonNode("createAccount", "technicalSkills");
        String[] technicalSkills = StreamSupport.stream(skillsNode.spliterator(), false)
                                                .map(JsonNode::asText)
                                                .toArray(String[]::new);

        return new Object[][]{
            {
                reader.getValue("createAccount", "firstName"),
                reader.getValue("createAccount", "middleName"),
                reader.getValue("createAccount", "lastName"),
                reader.getValue("createAccount", "email"),
                reader.getValue("createAccount", "phoneNumber"),
                reader.getValue("createAccount", "totalExperience"),
                reader.getValue("createAccount", "currentLocation"),
                reader.getValue("createAccount", "password"),
                reader.getValue("createAccount", "gender"),
                technicalSkills, // Pass array directly
                reader.getValue("createAccount", "resumePath"),
                Boolean.parseBoolean(reader.getValue("createAccount", "agreeTerms"))
            }
        };
    }
}
