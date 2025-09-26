package com.imejob.dataprovider;

import org.testng.annotations.DataProvider;

import utility.JsonReader;

public class BookDemoDataProvider {
	
	@DataProvider(name = "bookDemoData")
	public static String[][] getBookDemoData() {
		JsonReader reader = new JsonReader();
        reader.loadJson("bookDemoData"); // JSON file name without .json
        return new String[][]  {
        		{
        			reader.getValue("fillForm", "email"),
        			reader.getValue("fillForm", "phoneNumber"),
        			reader.getValue("fillForm", "firstName"),
        			reader.getValue("fillForm", "lastName"),
        			reader.getValue("fillForm", "subject"),
        			reader.getValue("fillForm", "message")
        		}
        };
	}
}
