package helper;

import utility.JsonReader;

public class SignInDataProvider {
	
	static JsonReader jsonReader;
	public static Object[][] getSingInData() {
		jsonReader = new JsonReader();
		jsonReader.loadJson("signInData");
		Object[][] data = {
				{jsonReader.getValue("signIn", "email"),
				jsonReader.getValue("signIn", "password")}
		};
		return data;
	}
	
}
