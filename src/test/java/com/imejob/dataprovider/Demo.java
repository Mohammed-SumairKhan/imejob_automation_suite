package com.imejob.dataprovider;

import utility.JsonReader;

public class Demo {
		public static void main(String[] args) {
			JsonReader createAccountJsonReader = new JsonReader();
			createAccountJsonReader.loadJson("createAccountData");
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"firstName"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"middleName"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"lastName"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"email"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"phoneNumber"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"totalExperience"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"currentLocation"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"password"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"gender"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"technicalSkills"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"resumePath"));
			System.out.println(createAccountJsonReader.getValue("createAccount" ,"agreeTerms"));
		}
		
}
