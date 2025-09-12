package com.imejob.dataprovider;

import java.util.stream.StreamSupport;
import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import utility.JsonReader;
/**
 * Reads Data From the personalInformation.json 
 * And Create Data 
 */
public class PersonalInformationDataProvider {
	
	/**
	 * creates the data and
	 * @return data 
	 */
	@DataProvider(name = "getPersonalInfoData")
    public static Object[][] getCreateAccountData() {
        JsonReader reader = new JsonReader();
        reader.loadJson("personalInformation"); // JSON file name without .json

        // Get technicalSkills as array
        JsonNode skillsNode = reader.getJsonNode("personalInformation", "technicalSkills");
        String[] technicalSkills = StreamSupport.stream(skillsNode.spliterator(), false)
                                                .map(JsonNode::asText)
                                                .toArray(String[]::new);

        return new Object[][]{
            {
                reader.getValue("personalInformation", "firstName"),
                reader.getValue("personalInformation", "middleName"),
                reader.getValue("personalInformation", "lastName"),
                reader.getValue("personalInformation", "email"),
                reader.getValue("personalInformation", "phoneNumber"),
                reader.getValue("personalInformation", "experience"),
                reader.getValue("personalInformation", "location"),
                reader.getValue("personalInformation", "noticePeriod"),
                reader.getValue("personalInformation", "expectedCTC"),
                technicalSkills, // Pass array directly
                Boolean.parseBoolean(reader.getValue("personalInformation", "newResume")),
                reader.getValue("personalInformation", "resume"),
            }
        };
    }
}
