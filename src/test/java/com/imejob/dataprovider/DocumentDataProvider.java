package com.imejob.dataprovider;

import org.testng.annotations.DataProvider;

import utility.JsonReader;

public class DocumentDataProvider {
	@DataProvider(name = "documentData")
	public static Object[][] getDocumentData() {
		JsonReader reader = new JsonReader();
        reader.loadJson("document"); // JSON file name without .json
        return new String[][]  {
        		{
        			reader.getValue("documents", "categoryName"),
        			reader.getValue("documents", "filePath"),
        		}
        };
	}
}
